package be.ugent.sysdes2.reservation.adapters.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {

    static final String RESERVATION_CREATED = "reservation_created";

    @Output(RESERVATION_CREATED)
    MessageChannel reservationCreated();
}
