package be.ugent.sysdes2.reservation.domain;

import java.time.ZonedDateTime;
import java.util.List;

public class Reservation {

    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private int capacity;
    private List<String> halls;
    private int maxVisitors;
    private float ticketPrice;
    private ReservationStatus status;

    public Reservation(ZonedDateTime startDate, ZonedDateTime endDate,int capacity, List<String> halls, int maxVisitors, float ticketPrice, ReservationStatus status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;
        this.halls = halls;
        this.maxVisitors = maxVisitors;
        this.ticketPrice = ticketPrice;
        this.status = status;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<String> getHalls() {
        return halls;
    }

    public void setHalls(List<String> halls) {
        this.halls = halls;
    }

    public int getMaxVisitors() {
        return maxVisitors;
    }

    public void setMaxVisitors(int maxVisitors) {
        this.maxVisitors = maxVisitors;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
