package it.svitali.dashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.svitali.dashboard.model.Ticket;
import it.svitali.dashboard.repository.TicketRepository;

@Controller
@RequestMapping("/dashboard")
public class TicketController {
	
	@Autowired
	private TicketRepository ticketRepository;

	
	@GetMapping
	public String index(Model model, @RequestParam(name="titolo", required = false) String titolo) {
		
		List<Ticket> tickets = new ArrayList<>();
		
		if(titolo == null || titolo.isBlank()) {
			tickets = ticketRepository.findAll();
		} else {
			tickets = ticketRepository.findByTitolo(titolo);
		}		
		
		
		model.addAttribute("list", tickets);
		
		return "/tickets/index";
	}
	
    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") Integer ticketId, Model model) {
    	
    	model.addAttribute("ticket", ticketRepository.getReferenceById(ticketId));
    	
    	return "tickets/show";
    }
    
	

//fine classe	
}
