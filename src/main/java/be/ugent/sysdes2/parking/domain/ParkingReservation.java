package be.ugent.sysdes2.parking.domain;

import javax.persistence.*;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "reservations")
public class ParkingReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "parking_id")
    private Parking parking;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "capacity")
    private int capacity;

    private ParkingReservation() {
    }

    public ParkingReservation(Parking parking, LocalDate startDate, LocalDate endDate, int capacity) {
        this.parking = parking;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity  = capacity;
    }

    public long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return MessageFormat.format("reservationId: {0}\t startDate: {1} \t endDate: {2}\t capacity: {3}" , this.id, this.startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), this.endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), this.capacity);
    }

}
