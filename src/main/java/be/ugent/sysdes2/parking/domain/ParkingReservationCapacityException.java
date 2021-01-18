package be.ugent.sysdes2.parking.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class ParkingReservationCapacityException extends HttpServerErrorException {
    public ParkingReservationCapacityException() {
        super(HttpStatus.CONFLICT, "Parking capacity is insufficient for the given period");
    }
}
