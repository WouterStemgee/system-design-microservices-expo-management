package be.ugent.sysdev2.security;

import be.ugent.sysdev2.security.domain.Emergency;
import be.ugent.sysdev2.security.domain.type;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import be.ugent.sysdev2.security.adapters.messaging.MessageGateway;
import be.ugent.sysdev2.security.adapters.messaging.Channels;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
@EnableBinding(Channels.class)
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}


	@Bean
	public CommandLineRunner testMessagingGateway(Emergency emergency, MessageGateway gateway) {
		return (args) -> {

			emergency.setSeverity(1);
			type etype = type.FIRE;
			emergency.setType(etype);
			emergency.setSource("Brandmelder");


			gateway.resolveEmergency(emergency);
		};
	}

}