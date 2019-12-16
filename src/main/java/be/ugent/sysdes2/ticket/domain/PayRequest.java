package be.ugent.sysdes2.ticket.domain;

public class PayRequest {

    private final int eventId;
    private final String name;
    private final int numberOfTickets;
    private final float totalPrice;

    public PayRequest(int eventId, String name, int numberOfTickets, float totalPrice) {
        this.eventId = eventId;
        this.name = name;
        this.numberOfTickets = numberOfTickets;
        this.totalPrice = totalPrice;
    }

    public int getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}
