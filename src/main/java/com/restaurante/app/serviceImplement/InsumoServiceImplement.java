package com.restaurante.app.serviceImplement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restaurante.app.model.Insumo;
import com.restaurante.app.repository.InsumoRepository;
import com.restaurante.app.service.InsumoService;

@Service
public class InsumoServiceImplement implements InsumoService{
	
	@Autowired
	private InsumoRepository dao;

	@Override
	public ResponseEntity<Map<String, Object>> listarInsumos() {
		Map<String,Object> respuesta = new HashMap<>();	
		List<Insumo> insumos = dao.findAll();
		
		if(!insumos.isEmpty()) {
			respuesta.put("insumos", insumos);
			respuesta.put("mensaje", "Lista de insumos");
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje", "No existen registros");
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> listarInsumoPorId(long id) {
		Map<String,Object> respuesta = new HashMap<>();		
		Optional<Insumo> insumos = dao.findById(id);
		
		if(insumos.isPresent()) {
			respuesta.put("insumos", insumos);
			respuesta.put("mensaje", "Busqueda correcta");
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.ok().body(respuesta);	
		}else {
			respuesta.put("mensaje", "Sin registros con ID: " + id);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> registrarInsumo(Insumo insumo) {
		Map<String,Object> respuesta = new HashMap<>();				
		dao.save(insumo);
		respuesta.put("insumo", insumo);
		respuesta.put("mensaje", "Se registro correctamente el insumo");
		respuesta.put("status", HttpStatus.CREATED);
		respuesta.put("fecha", new Date());	
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}

	@Override
	public ResponseEntity<Map<String, Object>> actualizarInsumo(Insumo ins, Long id) {
		Map<String,Object> respuesta = new HashMap<>();	
		Optional<Insumo> insumoExiste = dao.findById(id);		
		if (insumoExiste.isPresent()) {
			Insumo insumo = insumoExiste.get();
			insumo.setNombre(ins.getNombre());
			insumo.setStock(ins.getStock());
			insumo.setUnidad_medida(ins.getUnidad_medida());
            dao.save(insumo);
            respuesta.put("insumo", insumo);
    		respuesta.put("mensaje", "Datos del insumo modificado");
    		respuesta.put("status", HttpStatus.CREATED);
    		respuesta.put("fecha", new Date());	
    		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }else {
        	respuesta.put("mensaje", "Sin registros con ID: " + id);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
	}

	@Override
	public ResponseEntity<Map<String, Object>> eliminarInsumo(long id) {
		Map<String,Object> respuesta = new HashMap<>();	
		Optional<Insumo> insumoExiste = dao.findById(id);	
		if (insumoExiste.isPresent()) {
			Insumo insumo = insumoExiste.get();
			dao.delete(insumo);
			respuesta.put("mensaje", "Insumo eliminado correctamente");
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("fecha", new Date());	

			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje", "Sin registros con ID: " + id);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}	
	}
	
}
