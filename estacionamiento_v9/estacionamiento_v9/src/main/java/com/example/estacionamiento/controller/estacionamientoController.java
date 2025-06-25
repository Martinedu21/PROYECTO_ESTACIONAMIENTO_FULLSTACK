package com.example.estacionamiento.controller;

import com.example.estacionamiento.model.estacionamiento;
import com.example.estacionamiento.service.estacionamientoService;

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
@RequestMapping("/api/estacionamientos")
@Tag(name = "Estacionamiento", description = "Operaciones relacionadas con los espacios de estacionamientos") 
public class estacionamientoController {
    @Autowired
    private estacionamientoService service;

    @PostMapping("/crear")
    @Operation(summary = "Crear un nuevo estacionamiento", description = "Creacion de un nuevo estacionamiento") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Estacionamiento creado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public ResponseEntity<estacionamiento> crear(@Valid @RequestBody estacionamiento nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(nuevo));
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar estacionamientos", description = "Lista todos los estacionamientos de la base de datos") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Estacionamiento generado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public List<estacionamiento> listar() {
        return service.listar();
    }

    @GetMapping("/buscar/{id}")
    @Operation(summary = "Buscar un estacionamiento por ID", description = "Muestra un estacionamiento a partir de la ID ingresada")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Estacionamiento encontrado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public ResponseEntity<estacionamiento> obtener(@PathVariable Integer id) {
        estacionamiento e = service.obtener(id);
        return e != null ? ResponseEntity.ok(e) : ResponseEntity.notFound().build();
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar estacionamiento", description = "Actualiza un estacionamiento a partir de la ID ingresada")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Estacionamiento actualizado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
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

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar estacionamiento", description = "Elimina un estacionamiento a partir de la ID ingresada")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Estacionamiento eliminado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
