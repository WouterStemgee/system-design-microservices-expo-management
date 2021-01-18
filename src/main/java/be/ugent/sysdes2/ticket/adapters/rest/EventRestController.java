package be.ugent.sysdes2.ticket.adapters.rest;

import be.ugent.sysdes2.ticket.domain.Event;
import be.ugent.sysdes2.ticket.domain.TicketService;
import be.ugent.sysdes2.ticket.persistence.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ticket")
@CrossOrigin(origins = "*")
public class EventRestController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    public EventRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("{eventId}/availability")
    public Event getAvailability(@PathVariable("eventId") String eventId) {
        return this.ticketService.getAvailability(eventId);
    }
}
