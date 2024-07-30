package it.svitali.dashboard.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="nota")
public class Nota {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Column(name="data_creazione")
	private LocalDateTime dataCreazione;
	
	@NotBlank(message ="la descrizione non può essere vuota")
	@Size(max=255, message="la descrizione non può superare i 255 caratteri")
	@Column(name="descrizione", nullable=false)
	private String notaTesto;
	
	@ManyToOne
	@JoinColumn(name="ticket_id", nullable=false)
	private Ticket ticket;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	//COSTRUTTORE
	public Nota(){
		dataCreazione = LocalDateTime.now();
	};

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}



	public LocalDateTime getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDateTime dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public String getNotaTesto() {
		return notaTesto;
	}

	public void setNotaTesto(String notaTesto) {
		this.notaTesto = notaTesto;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	
    //GETTER SETTER

	

}
