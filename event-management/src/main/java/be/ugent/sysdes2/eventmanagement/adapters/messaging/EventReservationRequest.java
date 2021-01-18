package be.ugent.sysdes2.eventmanagement.adapters.messaging;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.ZonedDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventReservationRequest {
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private int maxVisitors;
    private float ticketPrice;

    public EventReservationRequest(ZonedDateTime startDate, ZonedDateTime endDate, int maxVisitors, float ticketPrice) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxVisitors = maxVisitors;
        this.ticketPrice = ticketPrice;
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

    public int getMaxVisitors() {
        return maxVisitors;
    }

    public void setMaxVisitors(int maxVisitors) {
        this.maxVisitors = maxVisitors;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
