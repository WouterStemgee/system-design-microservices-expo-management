package be.ugent.sysdes2.foodanddrinks.domain;

import be.ugent.sysdes2.foodanddrinks.persistence.OrderRepository;
import be.ugent.sysdes2.foodanddrinks.persistence.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class FoodAndDrinksService {

    private static Logger logger = LoggerFactory.getLogger(FoodAndDrinksService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BadgeProxy badgeProxy;

    public Badge createOrder(Order order) throws ProductNotFoundException, PaymentFailedException, JSONException, JsonProcessingException {
        float totalPrice = 0f;
        for(LineItem lineItem : order.getLineItems()) {
            logger.info("Product: " + lineItem.getProductId() + ", amount: " + lineItem.getAmount());
            Product product = productRepository.findById(lineItem.getProductId()).orElse(null);
            if(product != null) {
                totalPrice += lineItem.getAmount() * product.getPrice();
            } else{
                throw new ProductNotFoundException(lineItem.getProductId());
            }
        }
        Transaction transaction = new Transaction(order.getBadgeId(),totalPrice);
        ResponseEntity<String> response = badgeProxy.decreaseBalance(transaction);
        if(response.getStatusCode() == HttpStatus.OK) {
            logger.info("Payment accepted!");
            orderRepository.save(order);
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(Objects.requireNonNull(response.getBody()), Map.class);
            return new Badge((int) map.get("badgeId"),(String) map.get("eventId"), ((Double) map.get("balance")).floatValue());
        }
        throw new PaymentFailedException();
    }
}
