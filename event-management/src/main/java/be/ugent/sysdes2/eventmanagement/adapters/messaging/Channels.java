package be.ugent.sysdes2.eventmanagement.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {

    static final String RESERVATION_CREATED = "reservation_created";
    static final String EVENT_CREATED = "event_created";
    static final String EVENT_ENDED = "event_ended";

    @Input(RESERVATION_CREATED)
    MessageChannel reservationCreated();

    @Output(EVENT_CREATED)
    MessageChannel eventCreated();

    @Output(EVENT_ENDED)
    MessageChannel eventEnded();
}
