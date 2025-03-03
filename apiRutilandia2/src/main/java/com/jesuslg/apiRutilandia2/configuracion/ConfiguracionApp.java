package com.jesuslg.apiRutilandia2.configuracion;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class ConfiguracionApp {
	
	@Bean
    public PasswordEncoder passwordEncoder() throws Exception {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())  // Deshabilita CSRF (solo para pruebas)
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/usuarios/**", "/api/mesas/**").permitAll()  // Permite acceso sin autenticación
	            .anyRequest().authenticated()
	        )
	        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	    return http.build();
	}
	
	
	
	
    
	
	 

}
