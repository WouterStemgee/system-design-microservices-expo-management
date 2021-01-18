package be.ugent.sysdes2.badge.domain;

import be.ugent.sysdes2.badge.adapters.external.BankAdapter;
import be.ugent.sysdes2.badge.persistence.BadgeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadgeService {

    private static Logger logger = LoggerFactory.getLogger(BadgeService.class);

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private BankAdapter bank;

    public void createBadge(Ticket ticket) {
        Badge badge = new Badge(ticket.getTicketId(),ticket.getEventId(),0f);
        this.badgeRepository.save(badge);
    }

    public Badge rechargeBadge(int badgeId, float amount) throws BadgeNotFoundException, PaymentFailedException {
        Badge badge = this.badgeRepository.findById(badgeId).orElse(null);
        if(badge != null) {
            if(bank.transactionVerified(amount)) {
                badge.setBalance(badge.getBalance() + amount);
                this.badgeRepository.save(badge);
                logger.info("Badge " + badgeId + " recharged with " + amount + " euros.");
                return badge;
            }
            throw new PaymentFailedException();
        }
        throw new BadgeNotFoundException(badgeId);
    }

    public Badge decreaseBalance(int badgeId, float amount) throws BadgeNotFoundException, InsufficientBalanceException {
        Badge badge = this.badgeRepository.findById(badgeId).orElse(null);
        if(badge != null) {
            float currentBalance = badge.getBalance();
            if(currentBalance - amount >= 0) {
                badge.setBalance(currentBalance - amount);
                this.badgeRepository.save(badge);
                logger.info("Balance of badge " + badgeId + " decreased with " + amount + " euros.");
                return badge;
            }
            throw new InsufficientBalanceException();
        }
        throw new BadgeNotFoundException(badgeId);
    }
}
