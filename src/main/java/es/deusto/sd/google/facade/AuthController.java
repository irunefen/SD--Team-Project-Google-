package es.deusto.sd.google.facade;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.deusto.sd.google.dto.LoginCredentialsDTO;
import es.deusto.sd.google.service.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {
	private AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@GetMapping("/users/exists")
	public ResponseEntity<?> exists(@RequestParam(name="email") String email) {
		return new ResponseEntity<>(Map.of("exists", authService.isEmailRegistered(email)), HttpStatus.OK);

	}
	
	@PostMapping("/login") 
	public ResponseEntity<?> login(@RequestBody LoginCredentialsDTO credentials) {
		boolean isAuthorized = authService.authorizeUser(credentials.getEmail(), credentials.getPassword());
		
		return new ResponseEntity<>(Map.of("authorized", isAuthorized), HttpStatus.OK);
	}
	 
}
