package be.ugent.sysdes2.badge.adapters.rest;

import be.ugent.sysdes2.badge.domain.Badge;
import be.ugent.sysdes2.badge.persistence.BadgeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("badge")
@CrossOrigin(origins = "*")
public class BadgeRestController {

    private final BadgeRepository badgeRepository;

    private static Logger logger = LoggerFactory.getLogger(BadgeRestController.class);

    @Autowired
    public BadgeRestController(BadgeRepository badgeRepository){
        this.badgeRepository = badgeRepository;
    }

    @GetMapping("{badgeId}")
    public Badge getBadge(@PathVariable("badgeId") int badgeId) {
        logger.info("Get badge with id " + badgeId);
        return this.badgeRepository.findById(badgeId).orElse(null);
    }

}
