package be.ugent.sysdes2.hallmanagement.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reservations")
public class HallReservation {

    @Id @GeneratedValue
    private int reservationId;
    private int hallId;
    private LocalDate startDate;
    private LocalDate endDate;

    public HallReservation() {}

    public HallReservation(int hallId, LocalDate startDate, LocalDate endDate) {
        this.hallId = hallId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getReservationId() {
        return reservationId;
    }    

    public int getHallId() {
        return hallId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}