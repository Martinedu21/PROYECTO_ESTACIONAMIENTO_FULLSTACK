package com.example.estacionamiento.controller;

import com.example.estacionamiento.model.vehiculo;
import com.example.estacionamiento.service.vehiculoService;

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
@RequestMapping("/api/vehiculos")
@Tag(name = "Vehiculo", description = "Operaciones relacionadas con los vehiculos") 
public class vehiculoController {
    @Autowired
    private vehiculoService service;

    @PostMapping("/crear")
    @Operation(summary = "Crear un nuevo vehiculo", description = "Creacion de un nuevo vehiculo") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Vehiculo creado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    }) 
    public ResponseEntity<vehiculo> crear(@Valid @RequestBody vehiculo nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(nuevo));
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar vehiculos", description = "Lista todos los vehiculos de la base de datos") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Listado generado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    }) 
    public List<vehiculo> listar() { return service.listar(); }

    @GetMapping("/buscar/{id}")
    @Operation(summary = "Buscar un vehiculo por ID", description = "Muestra un vehiculo a partir de la ID ingresada")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Vehiculo encontrado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    }) 
    public vehiculo obtener(@PathVariable Integer id) { return service.obtener(id); }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar vehiculo", description = "Actualiza un vehiculo a partir de la ID ingresada")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Vehiculo actualizado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    }) 
    public vehiculo actualizar(@PathVariable Integer id, @Valid @RequestBody vehiculo datos) {
        vehiculo v = service.obtener(id);
        v.setMarca(datos.getMarca());
        v.setModelo(datos.getModelo());
        v.setPatente(datos.getPatente());
        v.setTipo(datos.getTipo());
        return service.guardar(v);
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar vehiculo", description = "Elimina un vehiculo a partir de la ID ingresada")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Vehiculo eliminado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    }) 
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) { service.eliminar(id); }
}