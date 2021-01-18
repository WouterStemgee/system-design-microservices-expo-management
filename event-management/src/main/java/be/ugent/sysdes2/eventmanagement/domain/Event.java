package be.ugent.sysdes2.eventmanagement.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.text.MessageFormat;

@Document
public class Event {

    @Id
    private String id;
    private String startDate;
    private String endDate;
    private int maxVisitors;
    private float ticketPrice;

    public Event(String startDate, String endDate, int maxVisitors, float ticketPrice) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxVisitors = maxVisitors;
        this.ticketPrice = ticketPrice;
    }

    public String getEventId() {
        return id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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

    @Override
    public String toString() {
        return MessageFormat.format("eventId: {0}\t startDate: {1} \t endDate: {2}\t maxVisitors: {3}\t ticketPrice: {4}" , this.id, this.startDate.toString(), this.endDate.toString(), this.maxVisitors, this.ticketPrice);
    }
}
