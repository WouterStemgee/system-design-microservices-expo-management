package be.ugent.sysdes2.ticket.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final int ticketId;
    private final String eventId;
    private final String name;
    private String status;

    private Ticket(){
        this.ticketId = 0;
        this.eventId = "";
        this.name = null;
        this.status = null;
    }

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

    public void setStatus(String status) {
        this.status = status;
    }
}
