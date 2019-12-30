package be.ugent.sysdev2.multimedia;

import be.ugent.sysdev2.multimedia.adapters.messaging.Channels;
import be.ugent.sysdev2.multimedia.domain.MessageBoard;
import be.ugent.sysdev2.multimedia.domain.MessageBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
@EnableBinding(Channels.class)
public class MultimediaApplication {

	private static Logger logger = LoggerFactory.getLogger(MultimediaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MultimediaApplication.class, args);
	}


	/*@Bean
	public CommandLineRunner testDatabase(MessageBoardService service) {
		return (args) -> {
			service.createMessageBoard("FIRE");
			service.createMessageBoard("NUCLEAR_ATTACK");

			Iterable<MessageBoard> boards= service.getAllMessageBoards();
			for (MessageBoard board:boards) {
				logger.info(board.getBoardMessage());
			}
			logger.info("After loop");
		};
	}*/
}
