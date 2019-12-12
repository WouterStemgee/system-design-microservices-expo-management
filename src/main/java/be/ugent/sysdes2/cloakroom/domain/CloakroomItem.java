package be.ugent.sysdes2.cloakroom.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CloakroomItem {

    @Id
    private int itemId;
    private int badgeId;

    private CloakroomItem() {
    }

    public CloakroomItem(int itemId, int badgeId) {
        this.itemId = itemId;
        this.badgeId = badgeId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getBadgeId() {
        return badgeId;
    }

}
