package com.jesuslg.apiRutilandia2.dtos;

import java.time.Instant;

import com.jesuslg.apiRutilandia2.entidades.Usuario;

public class UsuarioDto {
	
	private Long id;
    private String nombre;
    private String email;
    private String tokenRecuperacion;
    private Instant fechaExpiracionToken;

    // Constructor vacío
    public UsuarioDto() {}

    // Constructor que recibe una entidad Usuario y convierte en DTO
    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.tokenRecuperacion = usuario.getTokenRecuperacion();
        this.fechaExpiracionToken = usuario.getFechaExpiracionToken();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

}
