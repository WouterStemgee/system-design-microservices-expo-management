package be.ugent.sysdev2.multimedia.adapters.messaging;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {

    //@Gateway(requestChannel= Channels.EMERGENCY)
    //public void resolveEmergency(EmergencyRequest request);

    @Gateway(requestChannel = Channels.EMERGENCY_RECEIVED)
    public void emergencyReceived(String message);


}
