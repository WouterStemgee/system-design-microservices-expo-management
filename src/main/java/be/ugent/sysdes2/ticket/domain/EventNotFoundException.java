package be.ugent.sysdes2.ticket.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class EventNotFoundException extends HttpServerErrorException {
    public EventNotFoundException(int eventId) {
        super(HttpStatus.NOT_FOUND, "Event with id " + eventId + " does not exist.");
    }
}
