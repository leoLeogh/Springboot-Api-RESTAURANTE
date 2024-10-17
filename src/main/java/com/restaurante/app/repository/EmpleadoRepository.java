package com.restaurante.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.app.model.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
}
