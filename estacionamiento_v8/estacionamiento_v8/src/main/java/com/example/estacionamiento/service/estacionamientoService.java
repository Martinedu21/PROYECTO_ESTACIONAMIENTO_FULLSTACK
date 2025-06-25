package com.example.estacionamiento.service;

import com.example.estacionamiento.model.estacionamiento;
import com.example.estacionamiento.repository.estacionamientoRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class estacionamientoService {

    private final estacionamientoRepository repo;
    public estacionamientoService(estacionamientoRepository repo) { this.repo = repo; }

    public estacionamiento guardar(estacionamiento e) { return repo.save(e); }

    public estacionamiento obtener(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("estacionamiento " + id + " ocupado"));
    }

    public List<estacionamiento> listar() { return repo.findAll(); }

    public void eliminar(Integer id) { repo.deleteById(id); }
}
