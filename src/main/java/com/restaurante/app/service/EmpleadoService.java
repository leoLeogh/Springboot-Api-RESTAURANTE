package com.restaurante.app.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.restaurante.app.model.Empleado;

public interface EmpleadoService {
	ResponseEntity<Map<String, Object>> listarEmpleados();
	ResponseEntity<Map<String, Object>> listarEmpleadoPorId(long id);
    ResponseEntity<Map<String, Object>> registrarEmpleado(Empleado empleado);
    ResponseEntity<Map<String, Object>> actualizarEmpleado(Empleado emp, Long id);
    ResponseEntity<Map<String, Object>> eliminarEmpleado(long id);
}
