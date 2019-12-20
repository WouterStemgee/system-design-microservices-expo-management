package be.ugent.sysdes2.ticket.adapters.messaging;

import be.ugent.sysdes2.ticket.domain.Event;
import be.ugent.sysdes2.ticket.domain.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class TicketCommandHandler {

    private static Logger logger = LoggerFactory.getLogger(TicketCommandHandler.class);

    private final TicketService ticketService;

    @Autowired
    public TicketCommandHandler(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @StreamListener(Channels.EVENT_CREATED)
    public void createEvent(Event event) {
        this.ticketService.createEvent(event);
        logger.info("Event created! EventId: " + event.getEventId());
    }
}
