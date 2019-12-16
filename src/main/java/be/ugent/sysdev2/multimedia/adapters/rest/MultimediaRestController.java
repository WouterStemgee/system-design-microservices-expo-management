package be.ugent.sysdev2.multimedia.adapters.rest;

import be.ugent.sysdev2.multimedia.domain.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("multimedia")
public class MultimediaRestController {

    private final MessageBoardService messageBoardService;

    @Autowired
    public MultimediaRestController(MessageBoardService messageBoardService) {
        this.messageBoardService = messageBoardService;
    }

    @PostMapping("/postMessage")
    public String postMessageToMessageBoard(@RequestParam("id") int id, @RequestParam("message") String message) {
        String status = messageBoardService.sendMessageToMessageBoard(id, message);
        return status;
    }
    @PostMapping("/postMessageToAll")
    public String postMessageToMessageBoard(@RequestParam("message") String message) {
        String status = messageBoardService.sendMessageToAllMessageBoards(message);
        return status;
    }

}
