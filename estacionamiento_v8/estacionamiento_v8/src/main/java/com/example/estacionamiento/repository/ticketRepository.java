package com.example.estacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estacionamiento.model.ticket;

public interface ticketRepository extends JpaRepository<ticket, Integer> {
    boolean existsByPatente(String patente);
}