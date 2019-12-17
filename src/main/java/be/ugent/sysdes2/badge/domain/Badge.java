package be.ugent.sysdes2.badge.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Badge {

    @Id
    private final int badgeId;
    private final int eventId;
    private float balance;

    private Badge(){
        this.badgeId = 0;
        this.eventId = 0;
        this.balance = 0;
    }

    public Badge(int badgeId, int eventId, float balance){
        this.badgeId = badgeId;
        this.eventId = eventId;
        this.balance = balance;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public int getEventId() {
        return eventId;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
