package be.ugent.sysdes2.tracking.adapters.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.sysdes2.tracking.domain.Task;
import be.ugent.sysdes2.tracking.domain.TrackingService;

@RestController
@CrossOrigin
@RequestMapping("/tracking")
public class TrackingRestController {
    private TrackingService trackingService;

    @Autowired
	private TrackingRestController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }
    
    @GetMapping()
    public List<Task> getProgress(@RequestParam String eventId) {
        return trackingService.getEventStatuses(eventId);
    }

    @PostMapping
    public void updateProgress(@RequestBody Task t) {
        trackingService.setNewEventStatus(t.getEventId(), t.getStatus());
    }
}