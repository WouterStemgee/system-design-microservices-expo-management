package be.ugent.sysdes2.foodanddrinks.persistence;

import be.ugent.sysdes2.foodanddrinks.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
