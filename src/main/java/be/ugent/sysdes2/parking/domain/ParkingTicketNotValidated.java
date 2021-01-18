package be.ugent.sysdes2.parking.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class ParkingTicketNotValidated extends HttpServerErrorException {
    public ParkingTicketNotValidated() {
        super(HttpStatus.PAYMENT_REQUIRED, "Parking ticket not validated");
    }
}
