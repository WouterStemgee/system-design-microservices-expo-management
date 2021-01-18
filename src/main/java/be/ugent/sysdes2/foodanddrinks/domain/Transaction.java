package be.ugent.sysdes2.foodanddrinks.domain;

public class Transaction {

    private final int badgeId;
    private final float amount;

    private Transaction() {
        this.badgeId = 0;
        this.amount = 0f;
    }

    public Transaction(int badgeId, float amount) {
        this.badgeId = badgeId;
        this.amount = amount;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public float getAmount() {
        return amount;
    }
}
