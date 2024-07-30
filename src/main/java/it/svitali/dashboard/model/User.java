package it.svitali.dashboard.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class User {
	
	@Id
	private Integer id;
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	@NotNull
	private String name;
	
	@NotNull
	private String surname;
	
	@OneToMany(mappedBy="user")
	private List<Nota> note;	
	
	
	//////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
 
	//manytomany ruoli

	
	

}
