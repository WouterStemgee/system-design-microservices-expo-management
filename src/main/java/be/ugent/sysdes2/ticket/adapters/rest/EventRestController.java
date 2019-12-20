package be.ugent.sysdes2.ticket.adapters.rest;

import be.ugent.sysdes2.ticket.domain.Event;
import be.ugent.sysdes2.ticket.persistence.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ticket")
public class EventRestController {

    private final EventRepository eventRepository;

    private static Logger logger = LoggerFactory.getLogger(EventRestController.class);

    @Autowired
    public EventRestController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("{eventId}/availability")
    public ResponseEntity<?> getAvailability(@PathVariable("eventId") int eventId) {
        logger.info("Get event availability with id " + eventId);
        Event event = this.eventRepository.findById(eventId).orElse(null);
        if(event != null){
            return new ResponseEntity<>(event, HttpStatus.OK);
        }
        return new ResponseEntity<>("Event with id " + eventId + " does not exist.", HttpStatus.NOT_FOUND);    }
}
