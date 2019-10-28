package model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Vol {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String numeroVol;

	@Enumerated(EnumType.STRING)
	@Column
	private Avion typeAvion;

	@Column
	private int nbPlace;

	@Column(name = "depart")
	private String villeDeDepart;

	@Column(name = "arrive")
	private String villeArrive;

	@Column
	private LocalDate dateToDepart;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "volReserver")
	private List<Reservation> reservationsClients;

	public Vol() {

	}

	public Vol(String numeroVol, Avion typeAvion, int nbPlace, String villeDeDepart, String villeArrive,
			LocalDate dateToDepart) {
		super();
		this.numeroVol = numeroVol;
		this.typeAvion = typeAvion;
		this.nbPlace = nbPlace;
		this.villeDeDepart = villeDeDepart;
		this.villeArrive = villeArrive;
		this.dateToDepart = dateToDepart;
	}

	
	
	public Vol(Long id, String numeroVol, Avion typeAvion, int nbPlace, String villeDeDepart, String villeArrive,
			LocalDate dateToDepart, List<Reservation> reservationsClients) {
		super();
		this.id = id;
		this.numeroVol = numeroVol;
		this.typeAvion = typeAvion;
		this.nbPlace = nbPlace;
		this.villeDeDepart = villeDeDepart;
		this.villeArrive = villeArrive;
		this.dateToDepart = dateToDepart;
		this.reservationsClients = reservationsClients;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroVol() {
		return numeroVol;
	}

	public void setNumeroVol(String numeroVol) {
		this.numeroVol = numeroVol;
	}

	public Avion getTypeAvion() {
		return typeAvion;
	}

	public void setTypeAvion(Avion typeAvion) {
		this.typeAvion = typeAvion;
	}

	public int getNbPlace() {
		return nbPlace;
	}

	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}

	public String getVilleDeDepart() {
		return villeDeDepart;
	}

	public void setVilleDeDepart(String villeDeDepart) {
		this.villeDeDepart = villeDeDepart;
	}

	public String getVilleArrive() {
		return villeArrive;
	}

	public void setVilleArrive(String villeArrive) {
		this.villeArrive = villeArrive;
	}

	public LocalDate getDateToDepart() {
		return dateToDepart;
	}

	public void setDateToDepart(LocalDate dateToDepart) {
		this.dateToDepart = dateToDepart;
	}

	public List<Reservation> getReservationsClients() {
		return reservationsClients;
	}

	public void setReservationsClients(List<Reservation> reservationsClients) {
		this.reservationsClients = reservationsClients;
	}
	
}
