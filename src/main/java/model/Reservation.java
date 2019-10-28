package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String nom;

	@Column
	private String prenom;

	@Column
	private int age;

	@ManyToOne
	private Vol volReserver;
	
	public Reservation() {
		
	}

	public Reservation(String nom, String prenom, int age, Vol num) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.volReserver = num;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Vol getVolReserver() {
		return volReserver;
	}

	public void setVolReserver(Vol volReserver) {
		this.volReserver = volReserver;
	}

}
