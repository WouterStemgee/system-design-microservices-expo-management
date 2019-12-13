package be.ugent.sysdes2.ticket.adapters;

import be.ugent.sysdes2.ticket.domain.Ticket;
import be.ugent.sysdes2.ticket.persistence.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ticket")
public class TicketRestController {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketRestController(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    @PostMapping("/buy")
    public Ticket buyEntranceTicket(@RequestBody Ticket ticket) {
        return this.ticketRepository.save(ticket);
    }

    @PutMapping("{ticketId}/validate")
    public ResponseEntity<?> validateEntranceTicket(@PathVariable("ticketId") int ticketId) {
        Ticket ticket = this.ticketRepository.findById(ticketId).orElse(null);
        if(ticket != null) {
            ticket.setStatus("VALIDATED");
            this.ticketRepository.save(ticket);
            return new ResponseEntity<>(ticket,HttpStatus.OK);
        }
        return new ResponseEntity<>("Ticket with id " + ticketId + " does not exist.",
                HttpStatus.NOT_FOUND);
    }
}
