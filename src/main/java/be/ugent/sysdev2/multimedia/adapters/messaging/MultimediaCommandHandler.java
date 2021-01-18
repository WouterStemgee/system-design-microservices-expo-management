package be.ugent.sysdev2.multimedia.adapters.messaging;

import be.ugent.sysdev2.multimedia.MultimediaApplication;
import be.ugent.sysdev2.multimedia.domain.MessageBoard;
import be.ugent.sysdev2.multimedia.domain.MessageBoardService;
import be.ugent.sysdev2.multimedia.persistence.MessageBoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultimediaCommandHandler {

    private final MessageBoardService messageBoardService;

    private static Logger logger = LoggerFactory.getLogger(MultimediaApplication.class);
    @Autowired
    public MultimediaCommandHandler(MessageBoardService messageBoardService){
        this.messageBoardService=messageBoardService;
    }

    @StreamListener(Channels.EMERGENCY)

    public void resolveEmergency(EmergencyRequest request) {
        logger.info("Emergency got through the channel!");
        messageBoardService.sendMessageToAllMessageBoards(request.getType());
    }
}

