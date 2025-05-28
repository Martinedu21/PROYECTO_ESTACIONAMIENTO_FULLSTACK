package com.example.estacionamiento.microservicioVehiculos.repository;

import com.example.estacionamiento.microservicioVehiculos.model.vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface vehiculoRepository extends JpaRepository<vehiculo, Integer> {
    boolean existsByPatente(String Patente);
}