package be.ugent.sysdes2.foodanddrinks.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class ProductNotFoundException extends HttpServerErrorException {
    public ProductNotFoundException(String productId) {
        super(HttpStatus.NOT_FOUND, "Product with id " + productId + " not found.");
    }
}
