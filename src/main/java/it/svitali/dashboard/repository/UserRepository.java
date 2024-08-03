package it.svitali.dashboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import it.svitali.dashboard.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

	@Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :role AND u.active = true")
	public List<User> findByRole(@Param("role") String role);

	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.active = :active WHERE u.username = :username")
	void updateUserStatus(@Param("active") boolean active, @Param("username") String username);

}
