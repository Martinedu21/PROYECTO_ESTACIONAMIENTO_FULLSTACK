package com.example.estacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estacionamiento.model.vehiculo;

public interface vehiculoRepository extends JpaRepository<vehiculo, Integer> {
    boolean existsByPatente(String Patente);
}