package be.ugent.sysdes2.parking.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class ParkingFullException extends HttpServerErrorException {
    public ParkingFullException() {
        super(HttpStatus.METHOD_NOT_ALLOWED, "Parking is full");
    }
}
