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

import com.restaurante.app.model.Empleado;
import com.restaurante.app.service.EmpleadoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/empleados")
public class EmpleadoController {

	@Autowired
	private EmpleadoService service;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> listar(){
		return service.listarEmpleados();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listaPorID(@PathVariable Long id){
		return service.listarEmpleadoPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> agregar(@Valid @RequestBody Empleado empleado){
		return service.registrarEmpleado(empleado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> editar(@RequestBody Empleado empleado,@PathVariable Long id){
		return service.actualizarEmpleado(empleado,id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id){
		return service.eliminarEmpleado(id);
	}
	
}
