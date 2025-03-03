package com.jesuslg.apiRutilandia2.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesuslg.apiRutilandia2.entidades.Mesa;
import com.jesuslg.apiRutilandia2.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {

	Optional<Usuario> findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	Optional<Usuario> findByTokenRecuperacion(String token);
}
