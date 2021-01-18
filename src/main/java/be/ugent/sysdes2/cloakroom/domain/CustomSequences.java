package be.ugent.sysdes2.cloakroom.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customSequences")
public class CustomSequences {
    @Id
    private String id;
    private int seq;

    public void setId(String id) {
        this.id = id;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getId() {
        return id;
    }

    public int getSeq() {
        return seq;
    }
}