package be.ugent.sysdes2.foodanddrinks.persistence;

import be.ugent.sysdes2.foodanddrinks.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
