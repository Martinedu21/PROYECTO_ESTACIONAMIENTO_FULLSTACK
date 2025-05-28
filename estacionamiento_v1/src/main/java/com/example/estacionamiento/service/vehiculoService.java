package com.example.estacionamiento.service;

import com.example.estacionamiento.model.vehiculo;
import com.example.estacionamiento.repository.vehiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class vehiculoService {

    private final vehiculoRepository repo;
    public vehiculoService(vehiculoRepository repo) { this.repo = repo; }

    public vehiculo guardar(vehiculo v) { return repo.save(v); }

    public vehiculo obtener(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("vehiculo " + id + " no encontrado"));
    }

    public List<vehiculo> listar() { return repo.findAll(); }

    public void eliminar(Integer id) { repo.deleteById(id); }
}