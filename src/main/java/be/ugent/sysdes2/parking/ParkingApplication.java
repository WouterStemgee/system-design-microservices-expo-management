package be.ugent.sysdes2.parking;

import be.ugent.sysdes2.parking.domain.ParkingReservation;
import be.ugent.sysdes2.parking.domain.ParkingService;
import be.ugent.sysdes2.parking.persistence.ParkingReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
public class ParkingApplication {

	private static Logger logger = LoggerFactory.getLogger(ParkingApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
	}

	@Bean
	public CommandLineRunner createParking(ParkingService service) {
		return (args) -> {
			service.addParking();
		};
	}

	@Bean
	public CommandLineRunner testReservation(ParkingService service, ParkingReservationRepository parkingReservationRepository) {
		return (args) -> {
			String strStartDate = "18/12/2018";
			String strEndDate = "19/12/2018";
			ZonedDateTime startDate = LocalDate.parse(strStartDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(ZoneId.of("Europe/Brussels"));
			ZonedDateTime endDate = LocalDate.parse(strEndDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(ZoneId.of("Europe/Brussels"));

			logger.info("startDate: " + startDate.toString());
			logger.info("endDate: " + endDate.toString());
			List<ParkingReservation> res = parkingReservationRepository.findByStartDateAndEndDate(startDate, endDate);
			for (ParkingReservation p : res) {
				logger.info(p.toString());
			}
		};
	}

/*	@Bean
	public CommandLineRunner populateDatabase(ParkingRepository parkingRepository, ParkingTicketRepository ticketRepository) {
		return (args) ->{
			logger.info("Populating database");

			parkingRepository.deleteAll();
			Parking parking = new Parking(500);
			parkingRepository.save(parking);


			ticketRepository.deleteAll();
			LocalDateTime currTime = LocalDateTime.now();
			//DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
			ParkingTicket t1 = new ParkingTicket(parking, LocalDateTime.of(currTime.getYear(), currTime.getMonth(), currTime.getDayOfMonth(), currTime.getHour(), currTime.getMinute(), currTime.getSecond()), ParkingTicket.TicketState.NOT_VALIDATED);
			ParkingTicket t2 = new ParkingTicket(parking, LocalDateTime.of(currTime.getYear(), currTime.getMonth(), currTime.getDayOfMonth(), currTime.getHour(), currTime.getMinute(), currTime.getSecond()), ParkingTicket.TicketState.NOT_VALIDATED);
			ParkingTicket t3 = new ParkingTicket(parking, LocalDateTime.of(currTime.getYear(), currTime.getMonth(), currTime.getDayOfMonth(), currTime.getHour(), currTime.getMinute(), currTime.getSecond()), ParkingTicket.TicketState.NOT_VALIDATED);

			ticketRepository.save(t1);
			ticketRepository.save(t2);
			ticketRepository.save(t3);
		};
	}*/
}
