package be.ugent.sysdes2.badge.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

    static final String TICKET_VALIDATED = "ticket_validated";

    @Input(TICKET_VALIDATED)
    SubscribableChannel ticketValidated();

}
