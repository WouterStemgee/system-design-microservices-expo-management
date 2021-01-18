package be.ugent.sysdev2.multimedia.domain;


import javax.persistence.*;

@Entity
@Table(name = "message_board")
public class MessageBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="message")
    private String boardMessage;

    private MessageBoard(){

    }
    public MessageBoard(int id,String boardMessage){
        this.id=id;
        this.boardMessage=boardMessage;
    }
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
