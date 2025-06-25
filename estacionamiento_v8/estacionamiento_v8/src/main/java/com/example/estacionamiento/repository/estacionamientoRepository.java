package com.example.estacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estacionamiento.model.estacionamiento;

public interface estacionamientoRepository extends JpaRepository<estacionamiento, Integer> {
    boolean existsByPatente(String patente);
}
