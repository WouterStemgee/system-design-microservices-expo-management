package be.ugent.sysdes2.tracking.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;
    private String description;
    @Enumerated(EnumType.STRING)
    private EventStatus status;
    private String eventId;

    public Task() {
    }

    public Task(String description, EventStatus status, String eventId) {
        this.description = description;
        this.status = status;
        this.eventId = eventId;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public EventStatus getStatus() {
        return status;
    }

    public String getEventId() {
        return eventId;
    }
}