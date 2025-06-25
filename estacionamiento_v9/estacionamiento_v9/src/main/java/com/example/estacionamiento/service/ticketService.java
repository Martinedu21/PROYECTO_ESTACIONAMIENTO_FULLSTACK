package com.example.estacionamiento.service;

import com.example.estacionamiento.model.ticket;
import com.example.estacionamiento.repository.ticketRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ticketService {

    private final ticketRepository repo;
    public ticketService(ticketRepository repo) { this.repo = repo; }

    public ticket guardar(ticket t) { return repo.save(t); }

    public ticket obtener(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ticket " + id + " no encontrado"));
    }

    public List<ticket> listar() { return repo.findAll(); }

    public void eliminar(Integer id) { repo.deleteById(id); }

    public List<ticket> findByTipo(String tipo) {
        return repo.findByTipo(tipo);
    }

    public List<ticket> findByValor(float valor) {
        return repo.findByValor(valor);
    }

    public List<ticket> findByFecha(Date fecha) {
        return repo.findByFecha(fecha);
    }

    public List<ticket> findByPatente(String patente) {
        return repo.findByPatente(patente);
    }

    public long countById(Integer idTicket) {
        return repo.countById(idTicket);
    }
}