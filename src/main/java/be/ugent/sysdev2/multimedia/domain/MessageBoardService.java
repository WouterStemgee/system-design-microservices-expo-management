package be.ugent.sysdev2.multimedia.domain;


import be.ugent.sysdev2.multimedia.MultimediaApplication;
import be.ugent.sysdev2.multimedia.adapters.messaging.EmergencyRequest;
import be.ugent.sysdev2.multimedia.persistence.MessageBoardInsertRepository;
import be.ugent.sysdev2.multimedia.persistence.MessageBoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageBoardService {
    private final MessageBoardRepository messageBoardRepository;
    private final MessageBoardInsertRepository messageBoardInsertRepository;

    private static Logger logger = LoggerFactory.getLogger(MultimediaApplication.class);
    @Autowired
    public MessageBoardService(MessageBoardRepository messageBoardRepository, MessageBoardInsertRepository messageBoardInsertRepository){
        this.messageBoardRepository=messageBoardRepository;
        this.messageBoardInsertRepository = messageBoardInsertRepository;
    }

    public void sendMessageToAllMessageBoards(String message) {
            logger.info("Emerengcy Occured!");
            Iterable<MessageBoard> boards = messageBoardRepository.findAll();
            for (MessageBoard board : boards) {
                board.setBoardMessage(message);
            }
            messageBoardRepository.saveAll(boards);
    }

    public void sendMessageToMessageBoard(int id,String message){
            MessageBoard messageBoard = messageBoardRepository.findById(id);
            messageBoard.setBoardMessage(message);
            messageBoardRepository.save(messageBoard);
    }

    public Iterable<MessageBoard> getAllMessageBoards(){
        return messageBoardRepository.findAllMessageboards();
    }

    public void createMessageBoard(String message){
        messageBoardInsertRepository.insert(message);
    }

}
