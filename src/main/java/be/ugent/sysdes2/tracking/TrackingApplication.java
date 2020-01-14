package be.ugent.sysdes2.tracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import be.ugent.sysdes2.tracking.adapters.messaging.Channels;

@SpringBootApplication
@EnableBinding(Channels.class)
public class TrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackingApplication.class, args);
	}

}
