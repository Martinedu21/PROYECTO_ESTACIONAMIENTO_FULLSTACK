package com.example.estacionamiento.controller;

import com.example.estacionamiento.model.ticket;
import com.example.estacionamiento.service.ticketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Ticket", description = "Operaciones relacionadas con los tickets") 
public class ticketController {
    @Autowired
    private ticketService service;

    @PostMapping("/crear")
    @Operation(summary = "Crear un nuevo ticket", description = "Creacion de un nuevo ticket") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ticket creado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public ResponseEntity<ticket> crear(@Valid @RequestBody ticket nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(nuevo));
    }

    public ResponseEntity<ticket> crear_nuevo(@Valid @RequestBody ticket nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(nuevo));
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar tickets", description = "Lista todos los tickets de la base de datos") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ticket generado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    }) 
    public List<ticket> listar() { return service.listar(); }

    @GetMapping("/buscar/{id}")
    @Operation(summary = "Buscar un ticket por ID", description = "Muestra un ticket a partir de la ID ingresada")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ticket encontrado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public ticket obtener(@PathVariable Integer id) { return service.obtener(id); }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar ticket", description = "Actualiza un ticket a partir de la ID ingresada")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ticket actualizado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public ticket actualizar(@PathVariable Integer id, @Valid @RequestBody ticket datos) {
        ticket t = service.obtener(id);
        t.setValor(datos.getValor());
        t.setTipo(datos.getTipo());
        t.setFecha(datos.getFecha());
        t.setPatente(datos.getPatente());
        return service.guardar(t);
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar ticket", description = "Elimina un ticket a partir de la ID ingresada")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ticket eliminado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    }) 
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) { service.eliminar(id); }
}