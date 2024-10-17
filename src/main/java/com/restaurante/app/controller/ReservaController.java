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

import com.restaurante.app.model.Reserva;
import com.restaurante.app.service.ReservaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/reservas")
public class ReservaController {
	
	@Autowired
	private ReservaService service;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> listar(){
		return service.listarReservas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listaPorID(@PathVariable Long id){
		return service.BuscarReservaPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> agregar(@Valid @RequestBody Reserva reserva){
		return service.registrarReserva(reserva);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> editar(@RequestBody Reserva reserva,@PathVariable Long id){
		return service.actualizarReserva(reserva,id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id){
		return service.eliminarReserva(id);
	}
	
}
