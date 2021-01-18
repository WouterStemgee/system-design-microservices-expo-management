package be.ugent.sysdes2.cloakroom.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CloakroomItem {

    @Id 
    private int itemId;
    private int badgeId;
    private int seq;

    private CloakroomItem() {
    }

    public CloakroomItem(int itemId, int badgeId) {
        this.itemId = itemId;
        this.badgeId = badgeId;
    }

    public CloakroomItem(int badgeId) {
        this.badgeId = badgeId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getItemId() {
        return itemId;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public int getSeq() {
        return seq;
    }

}
