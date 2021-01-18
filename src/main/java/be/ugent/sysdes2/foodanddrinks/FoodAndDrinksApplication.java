package be.ugent.sysdes2.foodanddrinks;

import be.ugent.sysdes2.foodanddrinks.domain.Product;
import be.ugent.sysdes2.foodanddrinks.persistence.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FoodAndDrinksApplication {

	private static Logger logger = LoggerFactory.getLogger(FoodAndDrinksApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FoodAndDrinksApplication.class, args);
	}

	@Bean
	public CommandLineRunner printTest() {
		return (args) ->{
			logger.info("CommandLineRunner WORKING");
		};
	}

	@Bean
	public CommandLineRunner populateDatabase(ProductRepository productRepository) {
		return (args) -> {
			productRepository.deleteAll();
			logger.info("populating with new data...");
			Product p1 = new Product("1","Jupiler",1f);
			Product p2 = new Product("2","Cola",1.5f);
			Product p3 = new Product("3","Water",1.5f);
			productRepository.save(p1);
			productRepository.save(p2);
			productRepository.save(p3);
		};
	}

}
