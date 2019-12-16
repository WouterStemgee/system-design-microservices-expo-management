package be.ugent.sysdev2.multimedia.domain;


import be.ugent.sysdev2.multimedia.adapters.messaging.EmergencyRequest;
import be.ugent.sysdev2.multimedia.persistence.MessageBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageBoardService {
    private final MessageBoardRepository messageBoardRepository;

    @Autowired
    public MessageBoardService(MessageBoardRepository messageBoardRepository){
        this.messageBoardRepository=messageBoardRepository;
    }

    public String sendMessageToAllMessageBoards(String message) {
        try {
            List<MessageBoard> boards = messageBoardRepository.findAll();
            for (MessageBoard board : boards) {
                board.setBoardMessage(message);
            }
            messageBoardRepository.saveAll(boards);
            return "Succes";
        }
        catch(Exception e){
            return "Failure: "+ e;
        }
    }

    public String sendMessageToMessageBoard(int id,String Message){
        try {
            MessageBoard messageBoard = messageBoardRepository.findById(id);
            messageBoard.setBoardMessage(Message);
            messageBoardRepository.save(messageBoard);
            return "Succes";
        }
        catch(Exception e){
            return "Failure: "+e;
        }

    }

}
