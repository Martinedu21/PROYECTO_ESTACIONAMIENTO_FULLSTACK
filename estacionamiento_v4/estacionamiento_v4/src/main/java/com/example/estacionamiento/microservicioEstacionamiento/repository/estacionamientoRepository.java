package com.example.estacionamiento.microservicioEstacionamiento.repository;

import com.example.estacionamiento.microservicioEstacionamiento.model.estacionamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface estacionamientoRepository extends JpaRepository<estacionamiento, Integer> {
    boolean existsByPatente(String patente);
}
