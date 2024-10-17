package com.restaurante.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.app.model.Insumo;
import com.restaurante.app.service.InsumoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/insumos")
public class InsumoController {

	@Autowired
	private InsumoService service;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> listar(){
		return service.listarInsumos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listaPorID(@PathVariable Long id){
		return service.listarInsumoPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> agregar(@Valid @RequestBody Insumo insumo){
		return service.registrarInsumo(insumo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> editar(@RequestBody Insumo insumo,@PathVariable Long id){
		return service.actualizarInsumo(insumo,id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id){
		return service.eliminarInsumo(id);
	}
	
}
