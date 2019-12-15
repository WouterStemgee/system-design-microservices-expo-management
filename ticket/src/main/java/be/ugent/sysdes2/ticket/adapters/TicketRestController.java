package be.ugent.sysdes2.ticket.adapters;

import be.ugent.sysdes2.ticket.domain.PayRequest;
import be.ugent.sysdes2.ticket.domain.Detail;
import be.ugent.sysdes2.ticket.domain.Ticket;
import be.ugent.sysdes2.ticket.domain.BuyRequest;
import be.ugent.sysdes2.ticket.helpers.Bank;
import be.ugent.sysdes2.ticket.persistence.DetailRepository;
import be.ugent.sysdes2.ticket.persistence.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("ticket")
public class TicketRestController {

    private final TicketRepository ticketRepository;
    private final DetailRepository detailRepository;

    @Autowired
    public TicketRestController(TicketRepository ticketRepository, DetailRepository detailRepository){
        this.ticketRepository = ticketRepository;
        this.detailRepository = detailRepository;
    }

    @PostMapping("/buy")
    public ResponseEntity<?> buyTicket(@RequestBody BuyRequest buyRequest) {
        Detail detail = this.detailRepository.findById(buyRequest.getEventId()).orElse(null);
        if(detail != null){
            if(detail.getAvailableTickets() - buyRequest.getNumberOfTickets() >= 0){
                return new ResponseEntity<>(new PayRequest(buyRequest.getEventId(),buyRequest.getName(),buyRequest.getNumberOfTickets(), buyRequest.getNumberOfTickets() * detail.getPrice()),HttpStatus.OK);
            }
            return new ResponseEntity<>("Not enough tickets available.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Event with id " + buyRequest.getEventId() + " does not exist.", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/pay")
    public ResponseEntity<?> processPayment(@RequestBody PayRequest payRequest) {
        Detail detail = this.detailRepository.findById(payRequest.getEventId()).orElse(null);
        if(detail != null){
            if(detail.getAvailableTickets() - payRequest.getNumberOfTickets() >= 0) {
                if(Bank.VerifyPayment(payRequest.getName(), payRequest.getTotalPrice())) {
                    this.detailRepository.updateAvailableTickets(detail.getAvailableTickets() - payRequest.getNumberOfTickets(), payRequest.getEventId());
                    ArrayList<Ticket> tickets = new ArrayList<>();
                    for (int i = 0; i < payRequest.getNumberOfTickets(); i++){
                        Ticket ticket = new Ticket(0,payRequest.getEventId(),payRequest.getName(),"UNVALIDATED");
                        tickets.add(ticket);
                    }
                    return new ResponseEntity<>(this.ticketRepository.saveAll(tickets), HttpStatus.OK);
                }
                return new ResponseEntity<>("Payment failed", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Not enough tickets available.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Event with id " + payRequest.getEventId() + " does not exist.", HttpStatus.NOT_FOUND);
    }

    @PutMapping("{ticketId}/validate")
    public ResponseEntity<?> validateTicket(@PathVariable("ticketId") int ticketId) {
        Ticket ticket = this.ticketRepository.findById(ticketId).orElse(null);
        if(ticket != null) {
            ticket.setStatus("VALIDATED");
            this.ticketRepository.save(ticket);
            return new ResponseEntity<>(ticket,HttpStatus.OK);
        }
        return new ResponseEntity<>("Ticket with id " + ticketId + " does not exist.", HttpStatus.NOT_FOUND);
    }
}
