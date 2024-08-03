package it.svitali.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.svitali.dashboard.repository.UserRepository;

@Service
public class UserService {
	
	  @Autowired
	    private UserRepository userRepository;

	    public void updateUserStatus(String username, boolean active) {
	        userRepository.updateUserStatus(active, username);
	    }

}
