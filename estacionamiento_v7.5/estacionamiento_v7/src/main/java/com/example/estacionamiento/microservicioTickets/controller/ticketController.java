package com.example.estacionamiento.microservicioTickets.controller;

import com.example.estacionamiento.microservicioTickets.model.ticket;
import com.example.estacionamiento.microservicioTickets.service.ticketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class ticketController {

    private final ticketService service;
    public ticketController(ticketService service) { this.service = service; }

    @PostMapping("/nuevo")
    public ResponseEntity<ticket> crear(@Valid @RequestBody ticket nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(nuevo));
    }

    public ResponseEntity<ticket> crear_nuevo(@Valid @RequestBody ticket nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(nuevo));
    }

    @GetMapping("/listatickets")
    public List<ticket> listar() { return service.listar(); }

    @GetMapping("/buscar/{id}")
    public ticket obtener(@PathVariable Integer id) { return service.obtener(id); }

    @PutMapping("/actualizar/{id}")
    public ticket actualizar(@PathVariable Integer id, @Valid @RequestBody ticket datos) {
        ticket t = service.obtener(id);
        t.setValor(datos.getValor());
        t.setTipo(datos.getTipo());
        t.setFecha(datos.getFecha());
        t.setPatente(datos.getPatente());
        return service.guardar(t);
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) { service.eliminar(id); }
}