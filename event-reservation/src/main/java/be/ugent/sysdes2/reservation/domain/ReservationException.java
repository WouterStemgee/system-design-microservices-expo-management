package be.ugent.sysdes2.reservation.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class ReservationException extends HttpServerErrorException {
    public ReservationException() {
        super(HttpStatus.METHOD_NOT_ALLOWED, "Reservation is not possible");
    }
}
