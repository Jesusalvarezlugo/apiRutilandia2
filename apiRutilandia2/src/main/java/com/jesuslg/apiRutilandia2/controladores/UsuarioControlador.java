
package com.jesuslg.apiRutilandia2.controladores;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jesuslg.apiRutilandia2.dtos.UsuarioDto;
import com.jesuslg.apiRutilandia2.entidades.Usuario;
import com.jesuslg.apiRutilandia2.servicios.MesaServicio;
import com.jesuslg.apiRutilandia2.servicios.UsuarioServicio;

/**
 * Clase controladora de la entidad Usuario
 */
@CrossOrigin(origins = "http://localhost:4200")//cambiar por 8082 cuando use java o 4200 cuando se use angular
@RestController
@RequestMapping("/api")
//http://miserver:8080/api/usuarios/recurso(los métodos de abajo)
public class UsuarioControlador {
	//Objeto de la clase UsuarioServicio para utilizar los métodos CRUD
	@Autowired
	private UsuarioServicio usuarioServicio;

	@Autowired
	 private PasswordEncoder contraseniaMetodo;
	@Autowired
	private MesaServicio mesaServicio;

	// Constructor para inyectar el componente MesaServicio
	//Contructor para inyectar el componente UsuarioServicio
	public UsuarioControlador(UsuarioServicio usuarioServicio, MesaServicio mesaServicio,BCryptPasswordEncoder passwordEncoder) {
        this.usuarioServicio = usuarioServicio;
        this.mesaServicio = mesaServicio;
        this.contraseniaMetodo = passwordEncoder;
    }
	
	public UsuarioControlador() {}
	private final String SECRET_KEY = "altair_006!";  // 
    private final long EXPIRATION_TIME = 3600000;//una hora
    private String generateToken(Usuario usuario) {
        // Obtener la fecha y hora de expiración del token
        long currentTimeMillis = System.currentTimeMillis();
        Date expirationDate = new Date(currentTimeMillis + EXPIRATION_TIME);

        // Generar el token JWT
        return JWT.create()
                .withSubject(usuario.getEmail())  // Aquí puedes poner el ID o el email del usuario
                //.withClaim("role", usuario.getRole())  // Puedes agregar más información del usuario como el rol
                .withIssuedAt(new Date(currentTimeMillis))  // Fecha de creación
                .withExpiresAt(expirationDate)  // Fecha de expiración
                .sign(Algorithm.HMAC256(SECRET_KEY));  // Firma con la clave secreta
    }
	
    
	/**
	 * Método que crea un usuario utilizando el objeto usuarioServicio  
	 * que contiene todos los métodos crud necesarios
	 * @param usuario
	 * @return usuario creado
	 */
	//POST
	//http://miserver:8082/api/usuarios/registrarUsuario
	//@PostMapping("/usuarios/registroUsuario") para java
  //@PostMapping("/registro") para angular
	@PostMapping("/usuarios/registroUsuario")
	public Usuario crearUsuario(@RequestBody Usuario usuario) {//El @RequestBody transforma lo que envia el cliente a un objeto java ya que el cliente envia la informacion en formato json 
		return usuarioServicio.crearUsuario(usuario);
	}
	
	/**
	 * Método para crear  usuario en angular pero encriptando la contraseña aqui.
	 * @param usuario
	 * @return
	 */
	@PostMapping("/usuarios/registroUsuarioAngular")
	public Usuario crearUsuarioAngular(@RequestBody Usuario usuario) {
		//El @RequestBody transforma lo que envia el cliente a un objeto java ya que el cliente envia la informacion en formato json 
		 
		 usuario.setContrasenia(contraseniaMetodo.encode(usuario.getContrasenia()));
		return usuarioServicio.crearUsuario(usuario);
	}
	
	//GET
	//http://miserver:8080/api/usuarios
	@GetMapping("/usuarios/obtenerUsuarios")
	public List<Usuario> buscaTodosUsuarios(){
		return usuarioServicio.BuscarTodosUsuarios();
	}
	
	//GET
	//http://miserver:8080/api/usuarios/3(valor id)
	@GetMapping("/{id}")
	public Usuario buscarUsuario(@PathVariable Long id) {
		return usuarioServicio.buscarPorId(id);
	}
	
	@DeleteMapping("/usuarios/eliminar/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("El ID del usuario es requerido");
        }

        boolean eliminado = usuarioServicio.eliminarUsuarioPorId(id);

        if (eliminado) {
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } else {
            return ResponseEntity.internalServerError().body("Error al eliminar el usuario");
        }
    }
	
	//PUT actualizar un usuario
	//
	
	@PutMapping("/usuarios/actualizarUsuario")
    public ResponseEntity<String> actualizarUsuario(@RequestBody Usuario usuario) {
        if (usuario.getId() == null) {
            return ResponseEntity.badRequest().body("El ID del usuario es requerido");
        }

        Usuario usuarioActualizado = usuarioServicio.actualizarUsuario(usuario);

        if (usuarioActualizado != null) {
            return ResponseEntity.ok("Usuario actualizado correctamente");
        } else {
            return ResponseEntity.internalServerError().body("Error al actualizar el usuario");
        }
    
	
	}
	
	 // Método para verificar si el email existe
    @GetMapping("/emailExiste")
    public ResponseEntity<Boolean> verificarEmail(@RequestParam("email") String email) {
        boolean existe = usuarioServicio.existeEmail(email);
        return ResponseEntity.ok(existe);  // Retorna "true" si el email existe, "false" si no existe
    }
	
	/**
	 * metodo para hacer el login
	 * @param loginRequest
	 * @return
	 */
	@PostMapping("/usuarios/login")
	public Map<String, String> login(@RequestBody Usuario loginRequest) {
	    String email = loginRequest.getEmail();
	    String rawPassword = loginRequest.getContrasenia(); 
	    
	    Optional<Usuario> usuario = usuarioServicio.buscarPorEmail(email);

	    if (usuario.isPresent() && contraseniaMetodo.matches(rawPassword, usuario.get().getContrasenia())) {
	        String token = generateToken(usuario.get());
	        return Map.of("token", token);
	    } else {
	        return Map.of("error", "Credenciales incorrectas");
	    }
	}
	
	@GetMapping("/usuarios/verificarContrasenia")
	public boolean verificarContrasenia(@RequestParam("email") String email, 
	                                     @RequestParam("contrasenia") String contrasenia) {
	    Optional<Usuario> usuario = usuarioServicio.buscarPorEmail(email);

	    if (usuario.isPresent()) {
	        // Verificar si la contraseña coincide (utiliza el método que ya tienes en la API)
	        return usuarioServicio.verifyPassword(contrasenia, usuario.get().getContrasenia());
	    }
	    return false;
	}
	
	@GetMapping("/usuarios/detalles")
	public ResponseEntity<Usuario> obtenerDetallesUsuario(@RequestParam("email") String email) {
	    Optional<Usuario> usuarioOptional = usuarioServicio.buscarPorEmail(email);
	    if (usuarioOptional.isPresent()) {
	        return ResponseEntity.ok(usuarioOptional.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@GetMapping("/usuarios/obtenerPorToken")
	public ResponseEntity<UsuarioDto> obtenerUsuarioPorToken(@RequestParam("token") String token) {
	    // Buscar usuario en la base de datos por el token
	    Optional<Usuario> usuarioOpt = usuarioServicio.buscarPorToken(token);

	    if (usuarioOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Si el token no existe
	    }

	    Usuario usuario = usuarioOpt.get();

	    // Verificar si el token ha expirado
	    if (usuario.getFechaExpiracionToken() == null || usuario.getFechaExpiracionToken().isBefore(Instant.now())) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Token expirado
	    }

	    // Convertir la entidad Usuario a UsuarioDto
	    UsuarioDto usuarioDto = new UsuarioDto(usuario);

	    return ResponseEntity.ok(usuarioDto);
	}
	
	
	
	


}
