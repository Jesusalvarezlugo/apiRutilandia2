package com.jesuslg.apiRutilandia2.entidades;

import java.sql.Date;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase que contiene la entidad usuario
 */
@Entity//Indica a Spring que esta clase se va a mappear como una tabla de base de datos
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "idUsuario")
	Long idUsuario;
	@Column(name = "nombre")
	String nombre="aaaaa";
	@Column(name = "apellidos")
	String apellidos="aaaaa";
	@Column(name="telefono")
	String telefono="aaaaa";
	@Column(name="email")
	String email="aaaaa";
	@Column(name="rol")
	String rol="aaaaa";
	@Column(name="contraseña")
	String contrasenia="aaaaa";
	@Column(name="tokenRecuperacion")
	String tokenRecuperacion="aaaaa";
	@Column(name="fechaExpiracionToken")
	Instant fechaExpiracionToken;
	
	

	

	

	//Getters y Setters
	public Long getId() {
		return idUsuario;
	}

	public void setId(Long id) {
		this.idUsuario = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getTokenRecuperacion() {
		return tokenRecuperacion;
	}

	public void setTokenRecuperacion(String tokenRecuperacion) {
		this.tokenRecuperacion = tokenRecuperacion;
	}
	public Instant getFechaExpiracionToken() {
		return fechaExpiracionToken;
	}

	public void setFechaExpiracionToken(Instant fechaExpiracionToken) {
		this.fechaExpiracionToken = fechaExpiracionToken;
	}
	
	
	//Contrsuctores
	public Usuario(Long id, String nombre, String apellidos) {
		super();
		this.idUsuario = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	public Usuario() {
		
	}

}
