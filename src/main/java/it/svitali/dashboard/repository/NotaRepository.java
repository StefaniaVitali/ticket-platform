package it.svitali.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.svitali.dashboard.model.Nota;
import it.svitali.dashboard.model.Ticket;

public interface NotaRepository extends JpaRepository<Nota, Integer>{
	
	public List<Nota> findByTicket(Ticket ticket);
	
	

}
