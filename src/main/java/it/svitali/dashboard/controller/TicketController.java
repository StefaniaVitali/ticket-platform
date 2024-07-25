package it.svitali.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.svitali.dashboard.repository.TicketRepository;

@Controller
@RequestMapping("/dashboard")
public class TicketController {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@GetMapping
	public String index() {
		return "/tickets/index";
	}
	

}
