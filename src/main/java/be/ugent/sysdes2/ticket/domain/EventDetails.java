package be.ugent.sysdes2.ticket.domain;

public class EventDetails {

    private final String id;
    private final int maxVisitors;
    private final float ticketPrice;

    public EventDetails(String id, int maxVisitors, float ticketPrice) {
        this.id = id;
        this.maxVisitors = maxVisitors;
        this.ticketPrice = ticketPrice;
    }

    public String getEventId() {
        return id;
    }

    public int getMaxVisitors() {
        return maxVisitors;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }
}
