package com.jesuslg.apiRutilandia2.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jesuslg.apiRutilandia2.entidades.Usuario;
import com.jesuslg.apiRutilandia2.repositorios.UsuarioRepositorio;



/**
 * Clase donde irá la lógica de los métodos CRUD
 */
@Service
public class UsuarioServicio {
	
	//Objeto para llamar a la interfaz PersonaRepositorio
	private UsuarioRepositorio usuarioRepositorio;
	private BCryptPasswordEncoder contraseniaMetodo;
	
	@Autowired
	public UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {
		super();
		this.usuarioRepositorio = usuarioRepositorio;
		this.contraseniaMetodo = contraseniaMetodo;
		
	}
	
	/**
	 * Método para guardar un usuario en la base de datos
	 * @param usuario
	 * @return guarda el usuario en la base de datos
	 */
	public Usuario crearUsuario(Usuario usuario) {
		return usuarioRepositorio.save(usuario);
	}
	
	/**
	 * Método que busca un usuario por el id
	 * @param id
	 * @return usuario encontrado
	 */
	public Usuario buscarPorId(Long id) {
		return usuarioRepositorio.findById(id).get();
	}
	
	/**
	 * Método que busca todos los usuarios
	 * @return todos los usuarios encontrados
	 */
	public List<Usuario> BuscarTodosUsuarios(){
		return usuarioRepositorio.findAll();
	}
	
	/**
	 * Método que borra un usuario por el id
	 * @param id
	 */
	/**public void eliminarUsuarioPorId(Long id) {
		usuarioRepositorio.deleteById(id);
	}*/
	
	public boolean eliminarUsuarioPorId(Long id) {
	    if (usuarioRepositorio.existsById(id)) {
	        usuarioRepositorio.deleteById(id);
	        return true;  // Retorna true si se elimina con éxito
	    }
	    return false;  // Retorna false si el usuario no existe
	}
	/**
	 * Método para actualizar un usuario
	 * @param usuario
	 * @return usuario actualizado
	 */
	public Usuario actualizarUsuario(Usuario usuario) {
		return usuarioRepositorio.save(usuario);
	}
	
	public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepositorio.findByEmail(email);
    }
	
	public boolean verifyPassword(String contraseniaRecibida, String contraseniaAlmacenada) {
        return contraseniaMetodo.matches(contraseniaRecibida, contraseniaAlmacenada);
    }
	
	public boolean existeEmail(String email) {
		return usuarioRepositorio.existsByEmail(email);
	}
	
	public Optional<Usuario> buscarPorToken(String token) {
	    return usuarioRepositorio.findByTokenRecuperacion(token);
	}
	
	
	
	
    

    

   
	

}

