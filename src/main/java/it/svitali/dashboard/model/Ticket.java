package it.svitali.dashboard.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@NotBlank(message ="Il titolo non può essere vuoto")
	@Column(name="titolo", nullable=false)
	private String titolo;
	
	@NotBlank(message ="la descrizione non può essere vuota")
	@Column(name="descrizione", nullable=false)
	private String descrizione;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="status", nullable=false)
	private TicketStatus ticketStatus;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Column(name="data_creazione")
	private LocalDateTime data;
	
	//COSTRUTTORE
	public Ticket(){
		data = LocalDateTime.now();
		ticketStatus = TicketStatus.NOT_STARTED;
	};	

	//GETTER E SETTER
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	

//	
}
