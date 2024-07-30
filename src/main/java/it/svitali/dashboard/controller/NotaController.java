package it.svitali.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.svitali.dashboard.model.Nota;
import it.svitali.dashboard.repository.NotaRepository;
import it.svitali.dashboard.repository.TicketRepository;

@Controller
@RequestMapping("/note")
public class NotaController {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private NotaRepository notaRepository;
	

//	@GetMapping("/create")
//	public String create(Model model) {
//		model.addAttribute("nota", new Nota());
//		model.addAttribute("list",notaRepository.findById(null));
//	}
	


}
