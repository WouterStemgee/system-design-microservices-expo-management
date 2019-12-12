package be.ugent.sysdes2.cloakroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import be.ugent.sysdes2.cloakroom.domain.CloakroomItem;
import be.ugent.sysdes2.cloakroom.persistence.CloakroomRepository;

@SpringBootApplication
public class CloakroomApplication {

	private static Logger logger = LoggerFactory.getLogger(CloakroomApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CloakroomApplication.class, args);
	}

	@Bean
	CommandLineRunner testRepository(CloakroomRepository cloakroomRepository)
	{
		return (args) -> {
			cloakroomRepository.save(new CloakroomItem(1,1));
			cloakroomRepository.save(new CloakroomItem(2,1));
			cloakroomRepository.save(new CloakroomItem(3,2));
			cloakroomRepository.save(new CloakroomItem(4,2));
			cloakroomRepository.save(new CloakroomItem(5,8));
			cloakroomRepository.findByBadgeId(1).forEach(System.out::println);
		};
	}
}
