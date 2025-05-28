package com.example.estacionamiento.microservicioTickets.repository;

import com.example.estacionamiento.microservicioTickets.model.ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ticketRepository extends JpaRepository<ticket, Integer> {
    boolean existsByValor(float Valor);
}