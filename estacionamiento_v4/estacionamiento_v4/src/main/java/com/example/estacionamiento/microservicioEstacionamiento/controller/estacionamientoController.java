package com.example.estacionamiento.microservicioEstacionamiento.controller;

import com.example.estacionamiento.microservicioEstacionamiento.model.estacionamiento;
import com.example.estacionamiento.microservicioEstacionamiento.service.estacionamientoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estacionamiento")
public class estacionamientoController {

    private final estacionamientoService service;

    public estacionamientoController(estacionamientoService service) {
        this.service = service;
    }


    @PostMapping("/entrada")
    public ResponseEntity<estacionamiento> crear(@Valid @RequestBody estacionamiento nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(nuevo));
    }

    @GetMapping("/listaestacionamientos")
    public List<estacionamiento> listar() {
        return service.listar();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<estacionamiento> obtener(@PathVariable Integer id) {
        estacionamiento e = service.obtener(id);
        return e != null ? ResponseEntity.ok(e) : ResponseEntity.notFound().build();
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<estacionamiento> actualizar(@PathVariable Integer id, @Valid @RequestBody estacionamiento datos) {
        estacionamiento e = service.obtener(id);
        if (e == null) {
            return ResponseEntity.notFound().build();
        }
        e.setNumeroEspacio(datos.getNumeroEspacio());
        e.setPatente(datos.getPatente());
        e.setDisponible(datos.isDisponible());
        return ResponseEntity.ok(service.guardar(e));
    }

    @DeleteMapping("/salida/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
