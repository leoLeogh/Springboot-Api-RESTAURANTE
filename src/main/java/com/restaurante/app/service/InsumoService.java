package com.restaurante.app.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.restaurante.app.model.Insumo;

public interface InsumoService {
	ResponseEntity<Map<String, Object>> listarInsumos();
	ResponseEntity<Map<String, Object>> listarInsumoPorId(long id);
    ResponseEntity<Map<String, Object>> registrarInsumo(Insumo insumo);
    ResponseEntity<Map<String, Object>> actualizarInsumo(Insumo ins, Long id);
    ResponseEntity<Map<String, Object>> eliminarInsumo(long id);
}
