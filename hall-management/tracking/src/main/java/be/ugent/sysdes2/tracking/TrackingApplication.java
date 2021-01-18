package be.ugent.sysdes2.tracking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import be.ugent.sysdes2.tracking.adapters.messaging.Channels;
import be.ugent.sysdes2.tracking.domain.EventStatus;
import be.ugent.sysdes2.tracking.domain.Task;
import be.ugent.sysdes2.tracking.persistence.TaskRepository;

@SpringBootApplication
@EnableBinding(Channels.class)
public class TrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackingApplication.class, args);
	}

	@Bean
	CommandLineRunner init(TaskRepository taskRepository)
	{
		return (args) -> {
			taskRepository.save(new Task("Test event created",EventStatus.CREATED,"test-id"));	
		};
	}

}
