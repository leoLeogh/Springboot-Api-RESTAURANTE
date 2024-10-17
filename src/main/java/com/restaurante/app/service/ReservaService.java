package com.restaurante.app.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.restaurante.app.model.Reserva;

public interface ReservaService {
	ResponseEntity<Map<String, Object>> listarReservas();
	ResponseEntity<Map<String, Object>> BuscarReservaPorId(long id);
    ResponseEntity<Map<String, Object>> registrarReserva(Reserva reserva);
    ResponseEntity<Map<String, Object>> actualizarReserva(Reserva res, Long id);
    ResponseEntity<Map<String, Object>> eliminarReserva(long id);
}
