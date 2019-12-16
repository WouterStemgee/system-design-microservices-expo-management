package be.ugent.sysdev2.multimedia.domain;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Table
public class MessageBoard {
    @PrimaryKey
    private int id;

    @Column("Message")
    private String boardMessage;

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
