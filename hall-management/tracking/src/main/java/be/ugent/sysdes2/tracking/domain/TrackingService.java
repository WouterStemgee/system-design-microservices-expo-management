package be.ugent.sysdes2.tracking.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.sysdes2.tracking.persistence.TaskRepository;

@Service
public class TrackingService {
    @Autowired
    private TaskRepository taskRepository;

    public Task setNewEventStatus(String eventId, EventStatus eventStatus) {
        return taskRepository.save(new Task(getDescription(eventStatus),eventStatus,eventId));
    }

    public List<Task> getEventStatuses(String eventId) {
        return taskRepository.findByEventId(eventId);
    }

    private String getDescription(EventStatus eventStatus) {
        switch(eventStatus) {
            case ENDED:
                return "Event ended";
            case CREATED:
                return "Event created";
            default:
                return "";
        }
    }
    
}