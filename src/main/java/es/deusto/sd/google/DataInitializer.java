package es.deusto.sd.google;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import es.deusto.sd.google.dao.UserRepository;
import es.deusto.sd.google.entity.User;

@Configuration
public class DataInitializer {
	
	private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
	
	@Bean
	@Transactional
	CommandLineRunner initData(UserRepository userRepository) {
		return args -> {
			// Database is already initialized
			if (userRepository.count() > 0) {
				logger.info("Database already initialized!");
				return;
			}
			
			// Create some users
			User johnDoe = new User("john.doe@gmail.com", "johndoe1234");
			User janeDoe = new User("jane.doe@gmail.com", "janedoe1234");
			User alice = new User("alice@gmail.com", "alice1234");
			User bob = new User("bob@gmail.com", "bob1234");
			
			userRepository.saveAll(List.of(johnDoe, janeDoe, alice, bob));
			
			logger.info("Users saved!");
		};
	}
}
