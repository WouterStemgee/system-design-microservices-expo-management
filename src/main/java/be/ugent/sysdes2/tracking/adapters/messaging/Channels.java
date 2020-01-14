package be.ugent.sysdes2.tracking.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface Channels {

    static final String EVENT_CREATED = "event_created";
    static final String EVENT_ENDED = "event_ended";

    @Input(EVENT_CREATED)
    MessageChannel eventCreated();

    @Input(EVENT_ENDED)
    MessageChannel eventEnded();
}