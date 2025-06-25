package com.example.estacionamiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estacionamiento.model.ticket;

public interface ticketRepository extends JpaRepository<ticket, Integer> {
    
    List<ticket> findByTipo(String tipo);

    List<ticket> findByValor(float valor);

    List<ticket> findByFecha(java.util.Date fecha);

    List<ticket> findByPatente(String patente);

    long countById(Integer idTicket);
    
}