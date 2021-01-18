package be.ugent.sysdes2.parking.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class ParkingTicketNotFoundException extends HttpServerErrorException {
    public ParkingTicketNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Parking ticket not found in database");
    }
}
