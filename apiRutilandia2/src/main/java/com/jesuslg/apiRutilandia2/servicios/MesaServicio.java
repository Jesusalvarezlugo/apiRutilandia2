package com.jesuslg.apiRutilandia2.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jesuslg.apiRutilandia2.entidades.Mesa;
import com.jesuslg.apiRutilandia2.repositorios.MesaRepositorio;

@Service
public class MesaServicio {
	
	private MesaRepositorio mesaRepositorio;
	
	public MesaServicio(MesaRepositorio mesaRepositorio) {
		super();
		this.mesaRepositorio = mesaRepositorio;
	}
	
	/**
	 * Método para guardar una mesa en la base de datos
	 * @param mesa
	 * @return guarda la mesa en la base de datos
	 */
	public Mesa crearMesa(Mesa mesa) {
		return mesaRepositorio.save(mesa);
	}
	
	/**
	 * Método que busca una mesa por el id
	 * @param id
	 * @return mesa encontrada
	 */
	public Mesa buscarPorId(Long id) {
		return mesaRepositorio.findById(id).get();
	}
	
	/**
	 * Método que busca todas las mesas
	 * @return todas las mesas encontradas
	 */
	public List<Mesa> BuscarTodasMesas() {
		return mesaRepositorio.findAll();
	}
	
	/**
	 * Método para eliminar una mesa por el id
	 * @param id
	 * @return true si la mesa fue eliminada con éxito, false si no existe
	 */
	public boolean eliminarMesaPorId(Long id) {
	    if (mesaRepositorio.existsById(id)) {
	        mesaRepositorio.deleteById(id);
	        return true;  // Retorna true si se elimina con éxito
	    }
	    return false;  // Retorna false si la mesa no existe
	}
	
	/**
	 * Método para actualizar una mesa
	 * @param mesa
	 * @return mesa actualizada
	 */
	public Mesa actualizarMesa(Mesa mesa) {
		return mesaRepositorio.save(mesa);
	}
}
