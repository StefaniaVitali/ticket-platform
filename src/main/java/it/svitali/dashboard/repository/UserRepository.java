package it.svitali.dashboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.svitali.dashboard.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	Optional<User> findByUsername(String username);
	
	@Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :role")
	public List<User> findByRole(@Param("role") String role);


}
