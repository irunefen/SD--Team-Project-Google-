package es.deusto.sd.google.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.deusto.sd.google.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	boolean existsByEmail(String email);
}
