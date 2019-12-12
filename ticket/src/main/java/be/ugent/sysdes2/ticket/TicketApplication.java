package be.ugent.sysdes2.ticket;

import be.ugent.sysdes2.ticket.domain.Ticket;
import be.ugent.sysdes2.ticket.persistence.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicketApplication {

	private static Logger logger = LoggerFactory.getLogger(TicketApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TicketApplication.class, args);
	}

	@Bean
	public CommandLineRunner printOneTicket() {
		return (args) ->{
			logger.info("Printing one ticket:");
			Ticket t = new Ticket(1,1,"Thomas Devriese", "UNVALIDATED");
			logger.info(t.getName());
		};
	}

	@Bean
	public CommandLineRunner checkDatabasePopulation(TicketRepository ticketRepository) {
		return (args) ->{
			logger.info("Checking database population. Printing all tickets...");
			ticketRepository.findAll().forEach((ticket) -> logger.info(ticket.getName()));
		};
	}

	@Bean
	public CommandLineRunner checkRepositoryMethods(TicketRepository ticketRepository) {
		return (args) ->{
			logger.info("Checking repository methods:");
			logger.info("Saving ticket...");
			Ticket ticket = new Ticket(0,1,"Thomas Devriese","UNVALIDATED");
			ticketRepository.save(ticket);
			ticketRepository.findAll().forEach((t) -> logger.info(t.getName()));
		};
	}

}
