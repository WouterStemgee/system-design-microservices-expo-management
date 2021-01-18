package be.ugent.sysdes2.ticket.adapters.messaging;

import be.ugent.sysdes2.ticket.domain.Ticket;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {

    @Gateway(requestChannel = Channels.TICKET_VALIDATED)
    public void emitTicketValidated(Ticket ticket);

}
