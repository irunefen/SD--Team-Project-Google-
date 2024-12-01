package es.deusto.sd.google.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import es.deusto.sd.google.dao.UserRepository;
import es.deusto.sd.google.entity.User;

@Service
public class AuthService {
	private UserRepository userRepository;
	
	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public boolean isEmailRegistered(String email) {
        return userRepository.existsByEmail(email);
    }
	
	public boolean authorizeUser(String email, String password) {
		Optional<User> user = userRepository.findByEmail(email);

		if (user.isPresent() && user.get().getPassword().equals(password)) {
			return true;
		}

		return false;
	}

}
