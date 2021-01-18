package be.ugent.sysdes2.foodanddrinks.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class PaymentFailedException extends HttpServerErrorException {
    public PaymentFailedException() {
        super(HttpStatus.NOT_ACCEPTABLE, "Payment failed.");
    }
}
