package be.ugent.sysdes2.badge.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class BadgeNotFoundException extends HttpServerErrorException {
    public BadgeNotFoundException(int badgeId) {
        super(HttpStatus.NOT_FOUND, "Badge with id " + badgeId + " does not exist.");
    }
}
