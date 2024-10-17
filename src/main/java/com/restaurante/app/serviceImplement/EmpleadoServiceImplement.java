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

import com.restaurante.app.model.Empleado;
import com.restaurante.app.repository.EmpleadoRepository;
import com.restaurante.app.service.EmpleadoService;

@Service
public class EmpleadoServiceImplement implements EmpleadoService{
	
	@Autowired
	private EmpleadoRepository dao;

	@Override
	public ResponseEntity<Map<String, Object>> listarEmpleados() {
		Map<String,Object> respuesta = new HashMap<>();	
		List<Empleado> empleados = dao.findAll();
		
		if(!empleados.isEmpty()) {
			respuesta.put("empleados", empleados);
			respuesta.put("mensaje", "Lista de empleados");
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
	public ResponseEntity<Map<String, Object>> listarEmpleadoPorId(long id) {
		Map<String,Object> respuesta = new HashMap<>();		
		Optional<Empleado> empleados = dao.findById(id);
		
		if(empleados.isPresent()) {
			respuesta.put("empleados", empleados);
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
	public ResponseEntity<Map<String, Object>> registrarEmpleado(Empleado empleado) {
		Map<String,Object> respuesta = new HashMap<>();				
		dao.save(empleado);
		respuesta.put("empleados", empleado);
		respuesta.put("mensaje", "Se registro correctamente el empleado");
		respuesta.put("status", HttpStatus.CREATED);
		respuesta.put("fecha", new Date());	
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);	
	}
	
	@Override
	public ResponseEntity<Map<String, Object>> actualizarEmpleado(Empleado emp, Long id) {
		Map<String,Object> respuesta = new HashMap<>();	
		Optional<Empleado> empleadoExiste = dao.findById(id);		
		if (empleadoExiste.isPresent()) {
			Empleado empleado = empleadoExiste.get();
			empleado.setNombre(emp.getNombre());
			empleado.setApellido(emp.getApellido());
			empleado.setCargo(emp.getCargo());
			empleado.setSalario(emp.getSalario());
			empleado.setFecha_contratacion(emp.getFecha_contratacion());
            dao.save(empleado);
            respuesta.put("empleados", empleado);
    		respuesta.put("mensaje", "Datos del empleado modificado");
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
	public ResponseEntity<Map<String, Object>> eliminarEmpleado(long id) {
		Map<String,Object> respuesta = new HashMap<>();	
		Optional<Empleado> empleadoExiste = dao.findById(id);	
		if (empleadoExiste.isPresent()) {
			Empleado empleado = empleadoExiste.get();
			dao.delete(empleado);
			respuesta.put("mensaje", "Empleado eliminado correctamente");
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
