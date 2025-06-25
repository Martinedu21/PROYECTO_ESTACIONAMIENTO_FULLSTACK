package com.example.estacionamiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estacionamiento.model.vehiculo;

public interface vehiculoRepository extends JpaRepository<vehiculo, Integer> {
    
    List<vehiculo> findByMarca(String marca);

    List<vehiculo> findByModelo(String modelo);

    List<vehiculo> findByPatente(String patente);

    List<vehiculo> findByTipo(String tipo);

}