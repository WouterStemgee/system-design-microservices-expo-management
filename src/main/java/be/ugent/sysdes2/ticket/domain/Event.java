package be.ugent.sysdes2.ticket.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {

    @Id
    private final String eventId;
    private int availableTickets;
    private float price;

    private Event(){
        this.eventId = "";
        this.availableTickets = 0;
        this.price = 0f;
    }

    public Event(String id, int numberOfTickets, float price){
        this.eventId = id;
        this.availableTickets = numberOfTickets;
        this.price = price;
    }

    public String getEventId() {
        return eventId;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public float getPrice() {
        return price;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }
}
