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

import com.restaurante.app.model.Reserva;
import com.restaurante.app.repository.ReservaRepository;
import com.restaurante.app.service.ReservaService;

@Service
public class ReservaServiceImplement implements ReservaService{
	
	@Autowired
	private ReservaRepository dao;

	@Override
	public ResponseEntity<Map<String, Object>> listarReservas() {
		Map<String,Object> respuesta = new HashMap<>();	
		List<Reserva> reservas = dao.findAll();
		
		if(!reservas.isEmpty()) {
			respuesta.put("reservas", reservas);
			respuesta.put("mensaje", "Lista de reservas");
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
	public ResponseEntity<Map<String, Object>> BuscarReservaPorId(long id) {
		Map<String,Object> respuesta = new HashMap<>();		
		Optional<Reserva> reserva = dao.findById(id);
		
		if(reserva.isPresent()) {
			respuesta.put("reserva", reserva);
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
	public ResponseEntity<Map<String, Object>> registrarReserva(Reserva reserva) {
		Map<String,Object> respuesta = new HashMap<>();				
		dao.save(reserva);
		respuesta.put("reserva", reserva);
		respuesta.put("mensaje", "Se registro correctamente la reserva");
		respuesta.put("status", HttpStatus.CREATED);
		respuesta.put("fecha", new Date());	
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}

	@Override
	public ResponseEntity<Map<String, Object>> actualizarReserva(Reserva res, Long id) {
		Map<String,Object> respuesta = new HashMap<>();	
		Optional<Reserva> reservaExiste = dao.findById(id);		
		if (reservaExiste.isPresent()) {
			Reserva reserva = reservaExiste.get();
			reserva.setNombre_cliente(res.getNombre_cliente());
			reserva.setNumero_asientos(res.getNumero_asientos());
			reserva.setFecha(res.getFecha());
            dao.save(reserva);
            respuesta.put("reserva", reserva);
    		respuesta.put("mensaje", "Datos de la reserva modificado");
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
	public ResponseEntity<Map<String, Object>> eliminarReserva(long id) {
		Map<String,Object> respuesta = new HashMap<>();	
		Optional<Reserva> reservaExiste = dao.findById(id);	
		if (reservaExiste.isPresent()) {
			Reserva reserva = reservaExiste.get();
			dao.delete(reserva);
			respuesta.put("mensaje", "Reserva eliminada correctamente");
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
