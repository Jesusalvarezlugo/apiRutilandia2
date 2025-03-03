package com.jesuslg.apiRutilandia2.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jesuslg.apiRutilandia2.entidades.Mesa;
import com.jesuslg.apiRutilandia2.servicios.MesaServicio;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api")
public class MesaControlador {

	private MesaServicio mesaServicio;

	// Constructor para inyectar el componente MesaServicio
	public MesaControlador(MesaServicio mesaServicio) {
		super();
		this.mesaServicio = mesaServicio;
	}
	
	/**
	 * Método que crea una mesa utilizando el objeto mesaServicio
	 * que contiene los métodos CRUD necesarios
	 * @param mesa
	 * @return mesa creada
	 */
	// POST
	// http://miserver:8082/api/mesas/crearMesa
	@PostMapping("/mesas/crearMesa")
	public Mesa crearMesa(@RequestBody Mesa mesa) {
		
		System.out.println("Datos recibidos en la API:");
	    System.out.println("Nombre: " + mesa.getNombreMesa());
	    System.out.println("Descripción: " + mesa.getDescripcionMesa());
		return mesaServicio.crearMesa(mesa);
	}
	
	/**
	 * Método que obtiene todas las mesas utilizando el objeto mesaServicio
	 * @return lista de mesas
	 */
	// GET
	// http://miserver:8082/api/mesas/obtenerMesas
	@GetMapping("/mesas/obtenerMesas")
	public List<Mesa> obtenerMesas() {
		return mesaServicio.BuscarTodasMesas();
	}
	
}
