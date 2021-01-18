package be.ugent.sysdes2.ticket;

import be.ugent.sysdes2.ticket.adapters.messaging.Channels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBinding(Channels.class)
public class TicketApplication {

	private static Logger logger = LoggerFactory.getLogger(TicketApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TicketApplication.class, args);
	}

	@Bean
	public CommandLineRunner printTest() {
		return (args) ->{
			logger.info("CommandLineRunner WORKING");
		};
	}
}
