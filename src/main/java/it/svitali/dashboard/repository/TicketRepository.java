package it.svitali.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.svitali.dashboard.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	
	public List<Ticket> findByTitolo(String titolo);
	
//
}
