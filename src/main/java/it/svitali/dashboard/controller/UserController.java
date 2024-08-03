package it.svitali.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.svitali.dashboard.repository.UserRepository;
import it.svitali.dashboard.service.UserService;

public class UserController {
	
	
	@Autowired
	private UserRepository userRepository;
	
	 @Autowired
	    private UserService userService;

	    @PostMapping("/updateUserStatus")
	    public String updateUserStatus(@RequestParam String username, @RequestParam boolean active) {
	        userService.updateUserStatus(username, active);
	        return "User status updated successfully";
	    }

}
