package it.svitali.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.svitali.dashboard.model.Categoria;
import it.svitali.dashboard.model.Ticket;
import it.svitali.dashboard.model.TicketStatus;
import it.svitali.dashboard.model.User;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	
	public List<Ticket> findByTitolo(String titolo);
	
	public List<User> findByUser (User user);
	
	@Query("SELECT t FROM Ticket t WHERE t.categoria.nome = :nome")
	public List<Ticket> findByCategoriaNome (@Param("nome") String nome);	
	
	public List<Ticket> findByCategoria(Categoria categoria);
	

	public List<Ticket> findByTicketStatus (TicketStatus status);
	
	@Query("SELECT t FROM Ticket t WHERE t.user.username = :username")
	public List<Ticket> findByUsername (@Param("username")String Username);
	
//
}
