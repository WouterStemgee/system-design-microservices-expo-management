package be.ugent.sysdes2.badge.domain;

public class Ticket {

    private final int ticketId;
    private final String eventId;
    private final String name;
    private String status;

    public Ticket(int ticketId, String eventId, String name, String status){
        this.ticketId = ticketId;
        this.eventId = eventId;
        this.name = name;
        this.status = status;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}
