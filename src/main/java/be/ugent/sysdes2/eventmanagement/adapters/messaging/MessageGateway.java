package be.ugent.sysdes2.eventmanagement.adapters.messaging;

import be.ugent.sysdes2.eventmanagement.domain.Event;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {

    @Gateway(requestChannel = Channels.EVENT_CREATED)
    public void emitEventCreated(Event event);

    @Gateway(requestChannel = Channels.EVENT_ENDED)
    public void emitEventEnded(Event event);

}
