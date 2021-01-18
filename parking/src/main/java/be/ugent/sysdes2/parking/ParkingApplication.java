package be.ugent.sysdes2.parking;

import be.ugent.sysdes2.parking.domain.ParkingReservation;
import be.ugent.sysdes2.parking.domain.ParkingService;
import be.ugent.sysdes2.parking.persistence.ParkingReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
}
