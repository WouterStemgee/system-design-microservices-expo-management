package be.ugent.sysdes2.badge;

import be.ugent.sysdes2.badge.adapters.messaging.Channels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

//@EnableBinding(Channels.class)
@SpringBootApplication
public class BadgeApplication {

	private static Logger logger = LoggerFactory.getLogger(BadgeApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(BadgeApplication.class, args);
	}

	@Bean
	public CommandLineRunner printTest() {
		return (args) ->{
			logger.info("CommandLineRunner WORKING");
		};
	}

}
