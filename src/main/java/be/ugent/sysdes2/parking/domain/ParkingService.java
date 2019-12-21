package be.ugent.sysdes2.parking.domain;

import be.ugent.sysdes2.parking.adapters.external.BankAdapter;
import be.ugent.sysdes2.parking.persistence.ParkingRepository;
import be.ugent.sysdes2.parking.persistence.ParkingReservationRepository;
import be.ugent.sysdes2.parking.persistence.ParkingTicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingService {

    private static Logger logger = LoggerFactory.getLogger(ParkingService.class);

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ParkingTicketRepository ticketRepository;

    @Autowired
    private ParkingReservationRepository reservationRepository;

    @Value("${parking.capacity}")
    private int PARKING_CAPACITY;

    @Value("${parking.price.hourly}")
    private double PARKING_PRICE_HOURLY;

    @Autowired
    private BankAdapter bank;

    private Parking parking;

    @Transactional
    public synchronized void addParking() {
        List<Parking> parkings = parkingRepository.findAll();
        if (parkings.isEmpty()) {
            Parking parking = new Parking(PARKING_CAPACITY);
            parkingRepository.save(parking);
            this.parking = parking;
            logger.info("No parking found, creating new parking: " + parking.toString());
        } else {
            this.parking = parkings.get(0);
            logger.info("Parking found, using existing parking: " + this.parking.toString());

        }
    }

    @Transactional
    public synchronized void addParkingTicket() throws ParkingFullException {
        if (parking.getCounter() < parking.getMaxCapacity()) {
            LocalDateTime currTime = LocalDateTime.now();
            ParkingTicket ticket = new ParkingTicket(parking, LocalDateTime.of(currTime.getYear(), currTime.getMonth(), currTime.getDayOfMonth(), currTime.getHour(), currTime.getMinute(), currTime.getSecond()).atZone(ZoneId.of("Europe/Brussels")), ParkingTicket.TicketState.NOT_VALIDATED);
            parking.incCounter();
            parkingRepository.save(parking);
            ticketRepository.save(ticket);
            logger.info("Parking ticket added: " + ticket.toString());
        } else {
            throw new ParkingFullException();
        }
    }

    @Transactional
    public synchronized void validateTicket(Long ticketId) throws ParkingTicketNotFoundException {
        if (ticketRepository.findById(ticketId).isPresent()) {
            ParkingTicket ticket = ticketRepository.findById(ticketId).get();
            if (ticket.getState() == ParkingTicket.TicketState.NOT_VALIDATED) {
                long hours = ChronoUnit.HOURS.between(ticket.getTimeOfArrival(), LocalDateTime.now());
                double ticketPrice = hours * PARKING_PRICE_HOURLY;
                if (bank.transactionVerified(ticketPrice)) {
                    ticket.setState(ParkingTicket.TicketState.VALIDATED);
                    ticketRepository.save(ticket);
                    logger.info("Parking ticket validated: " + ticket.toString() + " (€ " + ticketPrice + ")");
                }
            }
        } else {
            throw new ParkingTicketNotFoundException();
        }
    }

    @Transactional
    public synchronized void exitParking(Long ticketId) {
        if (ticketRepository.findById(ticketId).isPresent()) {
            ParkingTicket ticket = ticketRepository.findById(ticketId).get();
            if (ticket.getState() == ParkingTicket.TicketState.VALIDATED) {
                    parking.decCounter();
                    ticket.setState(ParkingTicket.TicketState.DISABLED);
                    ZonedDateTime currTime = LocalDateTime.now().atZone(ZoneId.of("Europe/Brussels"));
                    ticket.setTimeOfDeparture(currTime);
                    parkingRepository.save(parking);
                    ticketRepository.save(ticket);
                    logger.info("Parking ticket disabled: " + ticket.toString());
            } else {
                throw new ParkingTicketNotValidated();
            }
        } else {
            throw new ParkingTicketNotFoundException();
        }
    }

    public void checkCapacity(ZonedDateTime startDate, ZonedDateTime endDate, int requestedCapacity) {
        // vraag alle reservaties op die eindigen na de startdatum, en beginnen voor de einddatum
        List<ParkingReservation> reservations = reservationRepository.findByStartDateAndEndDate(startDate, endDate);
        List<ParkingReservation> currDateReservations = new ArrayList<>();

        logger.info("reservations: " + reservations.toString());
        if (startDate.equals(endDate)) {
            currDateReservations.addAll(reservations);
        } else {
            for (LocalDate currDate = startDate.toLocalDate(); currDate.isBefore(endDate.toLocalDate()); currDate = currDate.plusDays(1)) {
                // verzameling maken van alle reservaties op elke dag tussen startdatum en einddatum, en telkens de som van de capaciteiten nemen
                for (ParkingReservation reservation : reservations) {
                    if ((reservation.getStartDate().toLocalDate().isBefore(currDate) || reservation.getStartDate().toLocalDate().isEqual(currDate)) && (reservation.getEndDate().toLocalDate().isAfter(currDate) || reservation.getEndDate().toLocalDate().isEqual(currDate))) {
                        currDateReservations.add(reservation);
                    }
                }
            }
        }

        logger.info("currDateReservations: " + currDateReservations.toString());

        // indien voor elke dag, het verschil tussen de maximale capaciteit van de parking en de gevraagde capaciteit
        // groter of gelijk is aan de som van de capaciteiten van de reservaties op elke dag, return true
        int reservedCapacity = 0;
        for(ParkingReservation reservation : currDateReservations) {
            reservedCapacity += reservation.getCapacity();
        }
        logger.info("reservedCapacity: " + reservedCapacity);
        if (parking.getMaxCapacity() - requestedCapacity < reservedCapacity) {
            throw new ParkingReservationCapacityException();
        }
    }

    public void reserveCapacity(ZonedDateTime startDate, ZonedDateTime endDate, int capacity) {
        this.checkCapacity(startDate, endDate, capacity);
        ParkingReservation reservation = new ParkingReservation(this.parking, startDate, endDate, capacity);
        reservationRepository.save(reservation);
        logger.info("Parking reservation reserved: " + reservation.toString());
    }
}
