package be.ugent.sysdev2.multimedia.adapters.rest;

import be.ugent.sysdev2.multimedia.domain.MessageBoard;
import be.ugent.sysdev2.multimedia.domain.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("multimedia")
public class MultimediaRestController {

    private final MessageBoardService messageBoardService;

    @Autowired
    public MultimediaRestController(MessageBoardService messageBoardService) {
        this.messageBoardService = messageBoardService;
    }

    @PostMapping("/postMessage")
    public void postMessageToMessageBoard(@RequestParam("id") int id, @RequestParam("message") String message) {
        messageBoardService.sendMessageToMessageBoard(id, message);
    }
    @PostMapping("/postMessageToAll")
    public void postMessageToMessageBoard(@RequestParam("message") String message) {
        messageBoardService.sendMessageToAllMessageBoards(message);
    }

    @GetMapping("/messageboards")
    public Iterable<MessageBoard> getAllMessageBoards(){
        Iterable<MessageBoard> boards = messageBoardService.getAllMessageBoards();
        return boards;
    }

}
