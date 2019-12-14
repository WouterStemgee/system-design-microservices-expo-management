package be.ugent.sysdev2.security.adapters.messaging;
import org.springframework.cloud.stream.annotation.Input;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

    static final String EMERGENCY = "emergency";
    static final String EMERGENCY_RECEIVED= "emergency_received";

    @Output(EMERGENCY)
    MessageChannel emergency();

    @Input(EMERGENCY_RECEIVED)
    SubscribableChannel emergencyReceived();

}
