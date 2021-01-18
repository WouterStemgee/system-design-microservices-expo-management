package be.ugent.sysdes2.tracking.adapters.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import be.ugent.sysdes2.tracking.domain.Event;
import be.ugent.sysdes2.tracking.domain.EventStatus;
import be.ugent.sysdes2.tracking.domain.TrackingService;

@Service
public class TrackingEventHandler {

	private static Logger logger = LoggerFactory.getLogger(TrackingEventHandler.class);
	private final TrackingService trackingService;
	
	@Autowired
	public TrackingEventHandler(TrackingService trackingService) {
		this.trackingService = trackingService;
	}
	
	@StreamListener(Channels.EVENT_CREATED)
	public void processEventCreated(Event event) {
        trackingService.setNewEventStatus(event.getEventId(), EventStatus.CREATED);
	}
	
	@StreamListener(Channels.EVENT_ENDED)
	public void processEventEnded(Event event) {
		trackingService.setNewEventStatus(event.getEventId(), EventStatus.ENDED);
	}
}
