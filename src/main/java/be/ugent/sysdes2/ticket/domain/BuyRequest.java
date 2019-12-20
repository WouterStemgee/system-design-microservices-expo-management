package be.ugent.sysdes2.ticket.domain;

public class BuyRequest {

    private final String eventId;
    private final String name;
    private final int numberOfTickets;

    public BuyRequest(String eventId, String name, int numberOfTickets) {
        this.eventId = eventId;
        this.name = name;
        this.numberOfTickets = numberOfTickets;
    }

    public String getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }
}
