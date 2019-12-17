package be.ugent.sysdes2.parking.domain;

import javax.persistence.*;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "tickets")
public class ParkingTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "parking_id")
    private Parking parking;

    @Column(name = "time_of_arrival")
    private LocalDateTime timeOfArrival;

    @Column(name = "time_of_departure", nullable = true)
    private LocalDateTime timeOfDeparture;

    @Column(name = "state")
    private TicketState state;

    private ParkingTicket() {
    }

    public ParkingTicket(Parking parking, LocalDateTime timeOfArrival, TicketState state) {
        this.parking = parking;
        this.timeOfArrival = timeOfArrival;
        this.timeOfDeparture = null;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getTimeOfArrival() {
        return timeOfArrival;
    }

    public LocalDateTime getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public TicketState getState() {
        return state;
    }

    public void setState(TicketState state) {
        this.state = state;
    }

    public void setTimeOfDeparture(LocalDateTime timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public enum TicketState {
        NOT_VALIDATED("NOT_VALIDATED"),
        VALIDATED("VALIDATED"),
        DISABLED("DISABLED");

        private final String state;

        TicketState(final String state) {
            this.state = state;
        }


        @Override
        public String toString() {
            return state;
        }
    }

    @Override
    public String toString() {
        return MessageFormat.format("ticketId: {0}\t State: {1} \t Date of arrival: {2} \t Date of departure: {3}" ,
                this.id,
                this.state.toString(),
                this.timeOfArrival.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")),
                this.timeOfDeparture == null ? "N/A" : this.timeOfDeparture.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")));
    }
}
