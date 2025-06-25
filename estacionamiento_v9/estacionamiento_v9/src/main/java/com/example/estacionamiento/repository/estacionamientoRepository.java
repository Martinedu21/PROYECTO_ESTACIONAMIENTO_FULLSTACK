package com.example.estacionamiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estacionamiento.model.estacionamiento;

public interface estacionamientoRepository extends JpaRepository<estacionamiento, Integer> {

    List<estacionamiento> findByNumeroEspacio(String numeroespacio);

    List<estacionamiento> findByPatente(String patente);

    List<estacionamiento> findByDisponible(boolean disponible);

}
