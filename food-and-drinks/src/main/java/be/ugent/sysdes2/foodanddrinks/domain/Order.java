package be.ugent.sysdes2.foodanddrinks.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
public class Order {

    @Id
    private final String orderId;
    private final String eventId;
    private final int badgeId;
    private final ArrayList<LineItem> lineItems;

    private Order() {
        this.orderId = "";
        this.eventId = "";
        this.badgeId = 0;
        this.lineItems = null;
    }

    public Order(String orderId, String eventId, int badgeId, ArrayList<LineItem> lineItems) {
        this.orderId = orderId;
        this.eventId = eventId;
        this.badgeId = badgeId;
        this.lineItems = lineItems;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getEventId() {
        return eventId;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }
}
