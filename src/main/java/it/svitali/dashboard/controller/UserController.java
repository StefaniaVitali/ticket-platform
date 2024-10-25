package it.svitali.dashboard.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

import it.svitali.dashboard.model.Ticket;
import it.svitali.dashboard.model.TicketStatus;
import it.svitali.dashboard.model.User;
import it.svitali.dashboard.repository.TicketRepository;
import it.svitali.dashboard.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired TicketRepository ticketRepository;
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer userId, Model model) {
		
	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String utenteCorrente = authentication.getName();
		Optional<User> user = userRepository.findByUsername(utenteCorrente);
		
		model.addAttribute("user", user.get());
		return "users/show";	
		
	
	}
	
	@PostMapping("/edit/{id}")
	public String edit(@ModelAttribute("user") User userForm) {
		

		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String utenteCorrente = authentication.getName();
		Optional<User> user = userRepository.findByUsername(utenteCorrente);	
		
		 List<Ticket> userTickets = ticketRepository.findByUser(userForm);
	        
       
	        
	        if(!userTickets.isEmpty()) {	        	 
	        	
	        	for(Ticket t : userTickets) {
	        		
	        		if(t.getTicketStatus() != TicketStatus.COMPLETED) {
	        			
	        			return "users/show";
	        			
	        			
	        		}
	        		
	        	}
	        }
	        

		userRepository.save(userForm);    	
    	
    	return "redirect:/dashboard";
    }
		
	



	//fine classe
}
