package com.jesuslg.apiRutilandia2.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="mesas")
public class Mesa {

	//Atribbutos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="idMesas")
	Long idMesa;
	
	@Column(name = "nombreMesa")
	String nombreMesa;
	@Column(name = "descripcionMesa")
	String descripcionMesa;
	
	//Getters y Setters
	
	public Long getIdMesa() {
		return idMesa;
	}
	public void setIdMesa(Long idMesa) {
		this.idMesa = idMesa;
	}
	public String getNombreMesa() {
		return nombreMesa;
	}
	public void setNombreMesa(String nombreMesa) {
		this.nombreMesa = nombreMesa;
	}
	public String getDescripcionMesa() {
		return descripcionMesa;
	}
	public void setDescripcionMesa(String descripcionMesa) {
		this.descripcionMesa = descripcionMesa;
	}
	
	//Constructores
	public Mesa(Long id, String nombreMesa, String descripcionMesa) {
		super();
		this.idMesa = id;
		this.nombreMesa = nombreMesa;
		this.descripcionMesa = descripcionMesa;
	}
	
	public Mesa() {
		
	}
}
