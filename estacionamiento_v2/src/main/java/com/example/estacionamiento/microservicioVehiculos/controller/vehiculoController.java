package com.example.estacionamiento.microservicioVehiculos.controller;

import com.example.estacionamiento.microservicioVehiculos.model.vehiculo;
import com.example.estacionamiento.microservicioVehiculos.service.vehiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculo")
public class vehiculoController {

    private final vehiculoService service;
    public vehiculoController(vehiculoService service) { this.service = service; }

    @PostMapping("/entrada")
    public ResponseEntity<vehiculo> crear(@Valid @RequestBody vehiculo nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(nuevo));
    }

    @GetMapping("/listavehiculos")
    public List<vehiculo> listar() { return service.listar(); }

    @GetMapping("/buscar/{id}")
    public vehiculo obtener(@PathVariable Integer id) { return service.obtener(id); }

    @PutMapping("/actualizar/{id}")
    public vehiculo actualizar(@PathVariable Integer id, @Valid @RequestBody vehiculo datos) {
        vehiculo v = service.obtener(id);
        v.setMarca(datos.getMarca());
        v.setModelo(datos.getModelo());
        v.setPatente(datos.getPatente());
        v.setTipo(datos.getTipo());
        return service.guardar(v);
    }

    @DeleteMapping("/salida/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) { service.eliminar(id); }
}