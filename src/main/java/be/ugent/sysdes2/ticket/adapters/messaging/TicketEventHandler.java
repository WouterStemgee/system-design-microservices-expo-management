package be.ugent.sysdes2.ticket.adapters.messaging;

import be.ugent.sysdes2.ticket.domain.Event;
import be.ugent.sysdes2.ticket.domain.EventDetails;
import be.ugent.sysdes2.ticket.domain.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class TicketEventHandler {

    private static Logger logger = LoggerFactory.getLogger(TicketEventHandler.class);

    private final TicketService ticketService;

    @Autowired
    public TicketEventHandler(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @StreamListener(Channels.EVENT_CREATED)
    public void createEvent(EventDetails eventDetails) {
        Event event = new Event(eventDetails.getEventId(),eventDetails.getMaxVisitors(),eventDetails.getTicketPrice());
        this.ticketService.createEvent(event);
        logger.info("Event created! EventId: " + event.getEventId());
    }

    @StreamListener(Channels.EVENT_ENDED)
    public void deleteEvent(EventDetails eventDetails) {
        this.ticketService.deleteEvent(eventDetails.getEventId());
        logger.info("Event deleted! Eventid: " + eventDetails.getEventId());
    }
}
