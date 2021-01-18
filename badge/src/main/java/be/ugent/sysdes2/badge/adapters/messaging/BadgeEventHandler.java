package be.ugent.sysdes2.badge.adapters.messaging;

import be.ugent.sysdes2.badge.domain.BadgeService;
import be.ugent.sysdes2.badge.domain.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class BadgeEventHandler {

    private static Logger logger = LoggerFactory.getLogger(BadgeEventHandler.class);

    private final BadgeService badgeService;

    @Autowired
    public BadgeEventHandler(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    @StreamListener(Channels.TICKET_VALIDATED)
    public void createBadge(Ticket ticket) {
        this.badgeService.createBadge(ticket);
        logger.info("Badge created! BadgeId: " + ticket.getTicketId());
    }
}
