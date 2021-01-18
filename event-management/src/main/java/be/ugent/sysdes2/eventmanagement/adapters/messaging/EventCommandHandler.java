package be.ugent.sysdes2.eventmanagement.adapters.messaging;

import be.ugent.sysdes2.eventmanagement.domain.Event;
import be.ugent.sysdes2.eventmanagement.domain.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class EventCommandHandler {
    private static Logger logger = LoggerFactory.getLogger(EventCommandHandler.class);
    @Autowired
    private EventService eventService;

    @StreamListener(Channels.RESERVATION_CREATED)
    @SendTo(Channels.EVENT_CREATED)
    public Event createEvent(EventReservationRequest request) {
        Event event = new Event(request.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), request.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), request.getMaxVisitors(), request.getTicketPrice());
        eventService.createEvent(event);
        logger.info("event created: " + event.toString());
        return event;
    }

}
