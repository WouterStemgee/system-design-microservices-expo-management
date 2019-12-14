package be.ugent.sysdev2.multimedia.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class MessageBoard {
    @Id
    @GeneratedValue
    private int id;
    private String boardMessage;

    public String getBoardMessage() {
        return boardMessage;
    }

    public void setBoardMessage(String boardMessage) {
        this.boardMessage = boardMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
