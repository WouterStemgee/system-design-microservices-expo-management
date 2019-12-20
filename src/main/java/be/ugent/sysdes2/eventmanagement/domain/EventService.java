package be.ugent.sysdes2.eventmanagement.domain;

import be.ugent.sysdes2.eventmanagement.adapters.messaging.MessageGateway;
import be.ugent.sysdes2.eventmanagement.persistance.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MessageGateway gateway;

    public Event getInformation(String eventId) {
        List<Event> event = eventRepository.findEventById(eventId);
        return event.get(0);
    }


    public void endEvent(String eventId) {
        List<Event> event = eventRepository.findEventById(eventId);
        gateway.emitEventEnded(event.get(0));
    }

    public void createEvent(Event event) {
        eventRepository.save(event);
    }
}
