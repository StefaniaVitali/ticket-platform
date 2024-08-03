package it.svitali.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.svitali.dashboard.model.Nota;
import it.svitali.dashboard.model.Ticket;
import it.svitali.dashboard.model.User;
import it.svitali.dashboard.repository.NotaRepository;
import it.svitali.dashboard.repository.TicketRepository;
import it.svitali.dashboard.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/note")
public class NotaController {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private NotaRepository notaRepository;
	
	@Autowired
	private UserRepository userRepository;
	

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("nota") Nota notaForm,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            Ticket ticket = ticketRepository.getReferenceById(notaForm.getTicket().getId());
            List<User> users = userRepository.findAll();
            model.addAttribute("ticket", ticket);
            model.addAttribute("utenti", users);
            return "note/create";
        }

        notaRepository.save(notaForm);
        return "redirect:/dashboard/show/" + notaForm.getTicket().getId();
    }
    
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer notaId) {    	
    	
    	Ticket ticket = notaRepository.getReferenceById(notaId).getTicket();
    	notaRepository.deleteById(notaId);
    	
    	return "redirect:/dashboard/show/" + ticket.getId() ; 
    	
    }

}

//
//if(bindingRisult.hasErrors()) {
//	model.addAttribute("categorie", categoriaRepository.findAll());
//	model.addAttribute("utenti", userRepository.findByRole("OPERATORE"));    		
//	return "tickets/create";
//}
//
// ticketRepository.save(ticketForm);
//
//return"redirect:/dashboard";
//}