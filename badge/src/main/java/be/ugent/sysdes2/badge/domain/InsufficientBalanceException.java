package be.ugent.sysdes2.badge.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class InsufficientBalanceException extends HttpServerErrorException {
    public InsufficientBalanceException() {
        super(HttpStatus.NOT_ACCEPTABLE, "Balance too low.");
    }
}
