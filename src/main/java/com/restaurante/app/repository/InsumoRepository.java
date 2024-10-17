package com.restaurante.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.app.model.Insumo;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long>{

}
