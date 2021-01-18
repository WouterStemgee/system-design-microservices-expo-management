package be.ugent.sysdev2.security.adapters.messaging;
import be.ugent.sysdev2.security.domain.Emergency;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {

    @Gateway(requestChannel = Channels.EMERGENCY)
    public void resolveEmergency(Emergency emergency);

}
