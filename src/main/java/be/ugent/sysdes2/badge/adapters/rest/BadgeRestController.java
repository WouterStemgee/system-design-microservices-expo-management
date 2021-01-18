package be.ugent.sysdes2.badge.adapters.rest;

import be.ugent.sysdes2.badge.domain.Badge;
import be.ugent.sysdes2.badge.domain.BadgeService;
import be.ugent.sysdes2.badge.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("badge")
@CrossOrigin(origins = "*")
public class BadgeRestController {

    @Autowired
    private BadgeService badgeService;

    @Autowired
    public BadgeRestController(BadgeService badgeService){
        this.badgeService = badgeService;
    }

    @PutMapping("{badgeId}/recharge")
    public Badge rechargeBadge(@PathVariable("badgeId") int badgeId, @RequestBody Transaction transaction) {
        return this.badgeService.rechargeBadge(badgeId, transaction.getAmount());
    }

    @PutMapping("{badgeId}/decrease")
    public Badge decreaseBalance(@PathVariable("badgeId") int badgeId, @RequestBody Transaction transaction) {
        return this.badgeService.decreaseBalance(badgeId, transaction.getAmount());
    }
}
