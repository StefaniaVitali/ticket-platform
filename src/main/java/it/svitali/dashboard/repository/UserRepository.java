package it.svitali.dashboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.svitali.dashboard.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	Optional<User> findByUsername(String username);

}
