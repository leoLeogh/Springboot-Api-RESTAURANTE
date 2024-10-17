package com.restaurante.app.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "reservas")
@Data
public class Reserva {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre_cliente;
    private int numero_asientos;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
}
