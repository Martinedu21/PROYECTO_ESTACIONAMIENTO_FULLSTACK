package com.example.estacionamiento.repository;

import com.example.estacionamiento.model.vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface vehiculoRepository extends JpaRepository<vehiculo, Integer> {
    boolean existsByPatente(String Patente);
}