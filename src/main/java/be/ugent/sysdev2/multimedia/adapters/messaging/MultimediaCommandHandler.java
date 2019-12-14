package be.ugent.sysdev2.multimedia.adapters.messaging;

import be.ugent.sysdev2.multimedia.domain.MessageBoard;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class MultimediaCommandHandler {

    @StreamListener(Channels.EMERGENCY)
    @SendTo(Channels.EMERGENCY_RECEIVED)
    public String resolveEmergency(EmergencyRequest request){
        MessageBoard board = new MessageBoard();
        board.setBoardMessage(request.getType());
        return "Message received";
    }
}

