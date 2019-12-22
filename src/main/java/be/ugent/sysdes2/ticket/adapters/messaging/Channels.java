package be.ugent.sysdes2.ticket.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

    static final String EVENT_CREATED = "event_created";
    static final String TICKET_VALIDATED = "ticket_validated";

    @Input(EVENT_CREATED)
    SubscribableChannel createEvent();

    @Output(TICKET_VALIDATED)
    MessageChannel ticketValidated();

}
