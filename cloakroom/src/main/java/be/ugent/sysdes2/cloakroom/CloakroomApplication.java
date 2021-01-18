package be.ugent.sysdes2.cloakroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import be.ugent.sysdes2.cloakroom.domain.CloakroomItem;
import be.ugent.sysdes2.cloakroom.domain.CloakroomSpace;
import be.ugent.sysdes2.cloakroom.persistence.CloakroomItemRepository;
import be.ugent.sysdes2.cloakroom.persistence.CloakroomSpaceRepository;

@SpringBootApplication
public class CloakroomApplication {

	private static Logger logger = LoggerFactory.getLogger(CloakroomApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CloakroomApplication.class, args);
	}

	@Bean
	CommandLineRunner init(CloakroomItemRepository cloakroomItemRepository, CloakroomSpaceRepository cloakroomSpaceRepository)
	{
		return (args) -> {
			CloakroomSpace cs = cloakroomSpaceRepository.getCloakroomSpace();
			if(cs == null) {
				cloakroomSpaceRepository.save(new CloakroomSpace(100,100,1.5f));
			} else {
				logger.info("{}/{} {}", cs.getAvailableSpaces(), cs.getTotalSpaces(), cs.getPricePerSpace());
			}			
		};
	}
}
