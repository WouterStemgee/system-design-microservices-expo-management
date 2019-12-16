package be.ugent.sysdes2.ticket.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Detail {

    @Id
    private final int eventId;
    private int availableTickets;
    private float price;

    private Detail(){
        this.eventId = 0;
        this.availableTickets = 0;
        this.price = 0;
    }

    public Detail(int id, int numberOfTickets, float price){
        this.eventId = id;
        this.availableTickets = numberOfTickets;
        this.price = price;
    }

    public int getEventId() {
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
