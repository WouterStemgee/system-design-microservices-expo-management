package be.ugent.sysdes2.ticket.adapters.rest;

import be.ugent.sysdes2.ticket.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("ticket")
@CrossOrigin(origins = "*")
public class TicketRestController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/buy")
    public PayRequest buyTicket(@RequestBody BuyRequest buyRequest) {
        return this.ticketService.buyTicket(buyRequest);
    }

    @PostMapping("/pay")
    public ArrayList<Ticket> processPayment(@RequestBody PayRequest payRequest) {
        return this.ticketService.processPayment(payRequest);
    }

    @PutMapping("{ticketId}/validate")
    public Ticket validateTicket(@PathVariable("ticketId") int ticketId) {
        return this.ticketService.validateTicket(ticketId);
    }
}
