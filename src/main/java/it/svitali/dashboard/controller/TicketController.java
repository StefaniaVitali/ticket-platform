package it.svitali.dashboard.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.svitali.dashboard.model.Categoria;
import it.svitali.dashboard.model.Nota;
import it.svitali.dashboard.model.Ticket;
import it.svitali.dashboard.model.User;
import it.svitali.dashboard.repository.CategoriaReposity;
import it.svitali.dashboard.repository.NotaRepository;
import it.svitali.dashboard.repository.TicketRepository;
import it.svitali.dashboard.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/dashboard")
public class TicketController {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private CategoriaReposity categoriaRepository;
	
	@Autowired
	private NotaRepository notaRepository;
	
	@Autowired
	private UserRepository userRepository;

	
	@GetMapping
	public String index(Model model, @RequestParam(name="titolo", required = false) String titolo) {
		
		List<Ticket> tickets = new ArrayList<>();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String utenteCorrente = authentication.getName();
		
		boolean isAdmin = false;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if ("ADMIN".equals(authority.getAuthority())) {
                isAdmin = true;
        		if(titolo == null || titolo.isBlank()) {
        			tickets = ticketRepository.findAll();
        		} else {
        			tickets = ticketRepository.findByTitolo(titolo);
        		}	
                break;
            } else {
            	tickets = ticketRepository.findByUsername(utenteCorrente);
            }
            
            
            
        }    
        
        

		model.addAttribute("list", tickets);
		
		return "/tickets/index";
	}
	
    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") Integer ticketId, Model model ) {
    	
    	
    	
    	Ticket t = ticketRepository.getReferenceById(ticketId);
    	model.addAttribute("note", notaRepository.findByTicket(t));
    	model.addAttribute("ticket", ticketRepository.getReferenceById(ticketId));
    	
    	
    	return "tickets/show";
    }
    
    @GetMapping("/create")
    public String create(Model model) {
    	
    	
    	model.addAttribute("ticket", new Ticket());
    	model.addAttribute("categorie", categoriaRepository.findAll());
    	model.addAttribute("utenti", userRepository.findByRole("OPERATORE"));
    	
    	return "tickets/create";    
    	}
    
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ticket") Ticket ticketForm, BindingResult bindingResult, 
    		Model model) { 
    	
    	if(bindingResult.hasErrors()) {
    		model.addAttribute("categorie", categoriaRepository.findAll());
        	model.addAttribute("utenti", userRepository.findByRole("OPERATORE"));    		
			return "tickets/create";
		}
    	
    	 ticketRepository.save(ticketForm);
    	
    	return"redirect:/dashboard";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer ticketId, Model model) {
    	
    	List<Categoria> categorie= categoriaRepository.findAll();
    	model.addAttribute("ticket", ticketRepository.findById(ticketId).get());
    	model.addAttribute("categorie", categorie);
    	model.addAttribute("utenti", userRepository.findByRole("OPERATORE"));    	
    	return"tickets/edit";
    }
    
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ticket") Ticket ticketForm,BindingResult bindingRisult,
    		Model model) {   
    	
    	if(bindingRisult.hasErrors()) {
    		model.addAttribute("categorie", categoriaRepository.findAll());
        	model.addAttribute("utenti", userRepository.findByRole("OPERATORE"));     		
			return "tickets/edit";
		}
    	
    	ticketRepository.save(ticketForm);
    	
    	return "redirect:/dashboard";
    }
    
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer ticketId) {  
    	
    	Ticket ticket = ticketRepository.findById(ticketId).get();
    	for(Nota nota : ticket.getNote()) {
    		notaRepository.delete(nota);
    	}
    	
    	ticketRepository.deleteById(ticketId);
    	
    	return "redirect:/dashboard";
    }
    
  @GetMapping("/show/{id}/note")
  public String note(@PathVariable("id") Integer ticketId, Model model) {
	  
	   Nota nota = new Nota();
	   Ticket ticket = ticketRepository.getReferenceById(ticketId);
	   List<User> user = userRepository.findAll();
	   nota.setTicket(ticket);
	   model.addAttribute("nota", nota);
	   model.addAttribute("ticket",ticket);
	   model.addAttribute("utenti", user);
	  
	  return"note/create";
	  
  }
    
    
	

//fine classe	
}
