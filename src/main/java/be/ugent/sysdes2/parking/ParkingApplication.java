package be.ugent.sysdes2.parking;

import be.ugent.sysdes2.parking.domain.Parking;
import be.ugent.sysdes2.parking.domain.ParkingService;
import be.ugent.sysdes2.parking.domain.ParkingTicket;
import be.ugent.sysdes2.parking.persistence.ParkingRepository;
import be.ugent.sysdes2.parking.persistence.ParkingTicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class ParkingApplication {

	private static Logger logger = LoggerFactory.getLogger(ParkingApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
	}

	@Bean
	public CommandLineRunner createParking(ParkingService service) {
		return (args) -> {
			service.addParking();
		};
	}

/*	@Bean
	public CommandLineRunner populateDatabase(ParkingRepository parkingRepository, ParkingTicketRepository ticketRepository) {
		return (args) ->{
			logger.info("Populating database");

			parkingRepository.deleteAll();
			Parking parking = new Parking(500);
			parkingRepository.save(parking);


			ticketRepository.deleteAll();
			LocalDateTime currTime = LocalDateTime.now();
			//DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
			ParkingTicket t1 = new ParkingTicket(parking, LocalDateTime.of(currTime.getYear(), currTime.getMonth(), currTime.getDayOfMonth(), currTime.getHour(), currTime.getMinute(), currTime.getSecond()), ParkingTicket.TicketState.NOT_VALIDATED);
			ParkingTicket t2 = new ParkingTicket(parking, LocalDateTime.of(currTime.getYear(), currTime.getMonth(), currTime.getDayOfMonth(), currTime.getHour(), currTime.getMinute(), currTime.getSecond()), ParkingTicket.TicketState.NOT_VALIDATED);
			ParkingTicket t3 = new ParkingTicket(parking, LocalDateTime.of(currTime.getYear(), currTime.getMonth(), currTime.getDayOfMonth(), currTime.getHour(), currTime.getMinute(), currTime.getSecond()), ParkingTicket.TicketState.NOT_VALIDATED);

			ticketRepository.save(t1);
			ticketRepository.save(t2);
			ticketRepository.save(t3);
		};
	}*/
}
