package be.ugent.sysdes2.reservation.domain;

import be.ugent.sysdes2.reservation.adapters.messaging.MessageGateway;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public class ReservationSaga {

    private static Logger logger = LoggerFactory.getLogger(ReservationSaga.class);

    private final HallManagementProxy hallManagement;
    private final ParkingProxy parking;
    private final MessageGateway gateway;

    @Autowired
    public ReservationSaga(HallManagementProxy hallManagement, ParkingProxy parkingProxy, MessageGateway gateway) {
        this.hallManagement = hallManagement;
        this.parking = parkingProxy;
        this.gateway = gateway;
    }

    public void startReservationSaga(Reservation reservation) throws JSONException {
        logger.info("Event reservation started. Requesting hall availability and parking capacity");
        reservation.setStatus(ReservationStatus.RESERVATION_PENDING);
        this.reserveParkingCapacity(reservation);
        //this.reserveHalls(reservation);
        this.reservationComplete(reservation);
    }

    public void reserveParkingCapacity(Reservation reservation) throws JSONException {
        if (!parking.reserve(reservation.getStartDate(), reservation.getEndDate(), reservation.getCapacity())) {
            logger.info("Event reservation failed: Parking capacity insufficient");
            this.reservationFailed(reservation);
        }
    }

    public void reserveHalls(Reservation reservation) throws JSONException {
        if (!hallManagement.reserve(reservation.getStartDate(), reservation.getEndDate(), reservation.getHalls())) {
            logger.info("Event reservation failed: Hall availability insufficient");
            this.reservationFailed(reservation);
        }
    }

    public void reservationFailed(Reservation reservation) {
        reservation.setStatus(ReservationStatus.RESERVATION_FAILED);
        this.reverseReservations(reservation);
        throw new ReservationException();
    }

    public void reverseReservations(Reservation reservation) {
        // Remove any reservations that have been made
    }

    public void reservationComplete(Reservation reservation) {
        logger.info("Event reservation completed successfully.");
        reservation.setStatus(ReservationStatus.RESERVED);
        this.gateway.emitReservationCreated(reservation);
    }

}
