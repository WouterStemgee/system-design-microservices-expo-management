package be.ugent.sysdev2.multimedia.adapters.messaging;

import be.ugent.sysdev2.multimedia.domain.MessageBoard;
import be.ugent.sysdev2.multimedia.domain.MessageBoardService;
import be.ugent.sysdev2.multimedia.persistence.MessageBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultimediaCommandHandler {

    private final MessageBoardService messageBoardService;

    @Autowired
    public MultimediaCommandHandler(MessageBoardService messageBoardService){
        this.messageBoardService=messageBoardService;
    }

    @StreamListener(Channels.EMERGENCY)
    @SendTo(Channels.EMERGENCY_RECEIVED)
    public String resolveEmergency(EmergencyRequest request){
        String status = messageBoardService.sendMessageToAllMessageBoards(request.getType());
        if(status.equals("succes")){
            return "Message has been send to the message boards";
        }
        else{
            return status;
        }
    }
}

