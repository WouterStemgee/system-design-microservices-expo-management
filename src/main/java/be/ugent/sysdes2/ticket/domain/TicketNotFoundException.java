package be.ugent.sysdes2.ticket.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class TicketNotFoundException extends HttpServerErrorException {
    public TicketNotFoundException(int ticketId) {
        super(HttpStatus.NOT_FOUND, "Ticket with id " + ticketId + " does not exist.");
    }
}
