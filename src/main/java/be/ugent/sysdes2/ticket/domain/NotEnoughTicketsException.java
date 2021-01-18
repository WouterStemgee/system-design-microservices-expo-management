package be.ugent.sysdes2.ticket.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class NotEnoughTicketsException extends HttpServerErrorException {
    public NotEnoughTicketsException() {
        super(HttpStatus.BAD_REQUEST, "Not enough tickets available.");
    }
}
