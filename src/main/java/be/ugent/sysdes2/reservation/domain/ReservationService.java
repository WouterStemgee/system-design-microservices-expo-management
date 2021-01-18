package be.ugent.sysdes2.reservation.domain;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private HallManagementProxy hallManagement;

    @Autowired
    private ParkingProxy parking;

    @Autowired
    private ReservationSaga reservationSaga;

    public void getAvailability(ZonedDateTime startDate, ZonedDateTime endDate, int capacity, List<String> halls) {
        if (!parking.getCapacity(startDate, endDate, capacity)) {
        //if (!parking.getCapacity(startDate, endDate, capacity) || !hallManagement.getAvailability(startDate, endDate, halls)) {
            throw new ReservationException();
        }
    }

    public void reserve(ZonedDateTime startDate, ZonedDateTime endDate, int capacity, List<String> halls, int maxVisitors, float ticketPrice) throws JSONException {
        if (parking.getCapacity(startDate, endDate, capacity)) {
        //if (parking.getCapacity(startDate, endDate, capacity) && hallManagement.getAvailability(startDate, endDate, halls)) {
            reservationSaga.startReservationSaga(new Reservation(startDate, endDate, capacity, halls, maxVisitors, ticketPrice, ReservationStatus.RESERVATION_REQUESTED));
        } else {
            throw new ReservationException();
        }
    }
}
