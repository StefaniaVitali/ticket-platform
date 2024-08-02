package it.svitali.dashboard.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.svitali.dashboard.model.Categoria;
import it.svitali.dashboard.model.Ticket;
import it.svitali.dashboard.model.TicketStatus;
import it.svitali.dashboard.repository.CategoriaReposity;
import it.svitali.dashboard.repository.TicketRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/dashboard")
public class TicketRestController {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private CategoriaReposity categoriaRepository;

	@GetMapping ("/tickets")
	public List<Ticket> indexTicket() {		
		   List<Ticket> tickets = new ArrayList<>();		   
		
		     return   tickets = ticketRepository.findAll();	    
		   

	}	
	
	
	   @GetMapping("/tickets/categoria")
	    public List<Ticket> indexTicketByCategory(@RequestParam(name = "name") String categoria) {
	        return ticketRepository.findByCategoriaNome(categoria);
	    }
	
	   @GetMapping("/tickets/status/{status}")
	   public List<Ticket> indexTicketByStatus(@PathVariable("status") TicketStatus ticketStatus){
		   return ticketRepository.findByTicketStatus(ticketStatus);
	    }
	
	
}
