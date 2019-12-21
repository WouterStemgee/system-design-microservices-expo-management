package be.ugent.sysdes2.eventmanagement.adapters.rest;

import be.ugent.sysdes2.eventmanagement.domain.Event;
import be.ugent.sysdes2.eventmanagement.domain.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@RestController
@CrossOrigin
@RequestMapping("event-management")
public class EventRestController {
    private static final Logger log = LoggerFactory.getLogger(EventRestController.class);

    @Autowired
    private EventService eventService;

    @GetMapping("/information")
    public Event getEventInformation(@RequestParam String eventId) throws JsonProcessingException {
        return eventService.getInformation(eventId);
    }

    @PostMapping("/end/{eventId}")
    public void endEvent(@PathVariable String eventId) {
        eventService.endEvent(eventId);
    }

}
