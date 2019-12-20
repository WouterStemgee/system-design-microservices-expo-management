package be.ugent.sysdes2.ticket.domain;

import be.ugent.sysdes2.ticket.adapters.external.BankAdapter;
import be.ugent.sysdes2.ticket.adapters.messaging.MessageGateway;
import be.ugent.sysdes2.ticket.persistence.EventRepository;
import be.ugent.sysdes2.ticket.persistence.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TicketService {

    private static Logger logger = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MessageGateway gateway;

    @Autowired
    private BankAdapter bank;

    public PayRequest buyTicket(BuyRequest buyRequest) throws NotEnoughTicketsException, EventNotFoundException {
        Event event = this.eventRepository.findById(buyRequest.getEventId()).orElse(null);
        if(event != null){
            if(event.getAvailableTickets() - buyRequest.getNumberOfTickets() >= 0){
                logger.info("New buyrequest for event " + buyRequest.getEventId() + ".");
                return new PayRequest(buyRequest.getEventId(),buyRequest.getName(),buyRequest.getNumberOfTickets(), buyRequest.getNumberOfTickets() * event.getPrice());
            }
            else {
                throw new NotEnoughTicketsException();
            }
        } else {
            throw new EventNotFoundException(buyRequest.getEventId());
        }
    }

    public ArrayList<Ticket> processPayment(PayRequest payRequest) throws PaymentFailedException, NotEnoughTicketsException, EventNotFoundException {
        Event event = this.eventRepository.findById(payRequest.getEventId()).orElse(null);
        if(event != null){
            if(event.getAvailableTickets() - payRequest.getNumberOfTickets() >= 0) {
                if(bank.transactionVerified(payRequest.getTotalPrice())) {
                    this.eventRepository.updateAvailableTickets(event.getAvailableTickets() - payRequest.getNumberOfTickets(), payRequest.getEventId());
                    ArrayList<Ticket> tickets = new ArrayList<>();
                    for (int i = 0; i < payRequest.getNumberOfTickets(); i++){
                        Ticket ticket = new Ticket(0,payRequest.getEventId(),payRequest.getName(),"UNVALIDATED");
                        tickets.add(ticket);
                    }
                    tickets = (ArrayList<Ticket>) this.ticketRepository.saveAll(tickets);
                    for(Ticket t: tickets) {
                        logger.info("Ticket for event " + t.getEventId() + " sold! TicketId: " + t.getTicketId());
                    }
                    return tickets;
                }
                throw new PaymentFailedException();
            }
            throw new NotEnoughTicketsException();
        }
        throw new EventNotFoundException(payRequest.getEventId());
    }

    public Ticket validateTicket(int ticketId) throws TicketNotFoundException {
        Ticket ticket = this.ticketRepository.findById(ticketId).orElse(null);
        if(ticket != null) {
            ticket.setStatus("VALIDATED");
            gateway.emitTicketValidated(ticket);
            this.ticketRepository.save(ticket);
            logger.info("Ticket " + ticketId + " for event " + ticket.getEventId() + " validated.");
            return ticket;
        }
        throw new TicketNotFoundException(ticketId);
    }

    public void createEvent(Event event) {
        this.eventRepository.save(event);
    }
}
