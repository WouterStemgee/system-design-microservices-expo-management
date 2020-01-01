package be.ugent.sysdes2.foodanddrinks.domain;

public class Badge {

    private final int badgeId;
    private final String eventId;
    private float balance;

    public Badge(int badgeId, String eventId, float balance){
        this.badgeId = badgeId;
        this.eventId = eventId;
        this.balance = balance;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public String getEventId() {
        return eventId;
    }

    public float getBalance() {
        return balance;
    }
}
