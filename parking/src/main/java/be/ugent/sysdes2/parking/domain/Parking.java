package be.ugent.sysdes2.parking.domain;

import javax.persistence.*;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "parkings")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parking_id")
    private long id;

    @Column(name = "capacity")
    private int maxCapacity;

    @Column(name = "count")
    private int counter;

    @OneToMany(mappedBy = "parking", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<ParkingTicket> tickets = new HashSet<>();

    @OneToMany(mappedBy = "parking", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<ParkingReservation> reservations = new HashSet<>();

    private Parking() {
    }

    public Parking(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.counter = 0;
    }

    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    public Set<ParkingTicket> getParkingTickets() {
        return this.tickets;
    }

    public Set<ParkingReservation> getParkingReservations() {
        return this.reservations;
    }

    @Override
    public String toString() {
        return MessageFormat.format("parkingId: {0}\t maxCapacity: {1}\t reservationCount: {2}\t counter: {3}", this.id, this.maxCapacity, this.reservations.size(), this.counter);
    }

    public int getCounter() {
        return counter;
    }

    public void incCounter() {
        this.counter++;
    }

    public void decCounter() {
        this.counter--;
    }
}
