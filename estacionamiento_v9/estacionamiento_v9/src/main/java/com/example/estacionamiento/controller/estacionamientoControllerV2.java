package com.example.estacionamiento.controller;

import com.example.estacionamiento.assemblers.EstacionamientoModelAssembler;
import com.example.estacionamiento.model.estacionamiento;
import com.example.estacionamiento.service.estacionamientoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/estacionamientos")
public class estacionamientoControllerV2 {

    @Autowired
    private estacionamientoService estacionamientoService;

    @Autowired
    private EstacionamientoModelAssembler assembler;

    
    @GetMapping(value = "/espacio/{numeroespacio}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar por numero de espacio", description = "Trae un estacionamiento por su numero de espacio") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Estacionamiento encontrado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public CollectionModel<EntityModel<estacionamiento>> getEstacionamientoByNumeroEspacio(@PathVariable String numeroespacio) {
        List<EntityModel<estacionamiento>> estacionamientos = estacionamientoService.findByNumeroEspacio(numeroespacio).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(estacionamientos,
                linkTo(methodOn(estacionamientoControllerV2.class).getEstacionamientoByNumeroEspacio(numeroespacio)).withSelfRel());
    }


    @GetMapping(value = "/patente/{patente}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar por patente", description = "Trae un estacionamiento por su patente") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Estacionamiento encontrado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public CollectionModel<EntityModel<estacionamiento>> getEstacionamientoByPatente(@PathVariable String patente) {
        List<EntityModel<estacionamiento>> estacionamientos = estacionamientoService.findByPatente(patente).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(estacionamientos,
                linkTo(methodOn(estacionamientoControllerV2.class).getEstacionamientoByPatente(patente)).withSelfRel());
    }

    @GetMapping(value = "/disponible/{true_o_false}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar por disponibilidad", description = "Trae un estacionamiento por su disponibilidad (true or false)") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Estacionamiento encontrado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public CollectionModel<EntityModel<estacionamiento>> getEstacionamientoByDisponible(@PathVariable boolean disponible) {
        List<EntityModel<estacionamiento>> estacionamientos = estacionamientoService.findByDisponible(disponible).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(estacionamientos,
                linkTo(methodOn(estacionamientoControllerV2.class).getEstacionamientoByDisponible(disponible)).withSelfRel());
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Traer todos los estacionamientos", description = "Lista todos los estacionamientos") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Estacionamientos listados!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public CollectionModel<EntityModel<estacionamiento>> getAllEstacionamientos() {
        List<EntityModel<estacionamiento>> estacionamientos = estacionamientoService.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(estacionamientos,
                linkTo(methodOn(estacionamientoControllerV2.class).getAllEstacionamientos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener estacionamiento por ID", description = "Trae un estacionamiento por su ID") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Estacionamiento encontrado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public EntityModel<estacionamiento> getEstacionamientoById(@PathVariable int id) {
        estacionamiento estacionamiento = estacionamientoService.obtener(id);
        return assembler.toModel(estacionamiento);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear estacionamiento", description = "Crea un nuevo estacionamiento") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Estacionamiento creado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public ResponseEntity<EntityModel<estacionamiento>> createEstacionamiento(@RequestBody estacionamiento estacionamiento) {
        estacionamiento newEstacionamiento = estacionamientoService.guardar(estacionamiento);
        return ResponseEntity
                .created(linkTo(methodOn(estacionamientoControllerV2.class).getEstacionamientoById(newEstacionamiento.getId())).toUri())
                .body(assembler.toModel(newEstacionamiento));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar estacionamiento", description = "Actualiza un estacionamiento por su ID") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Estacionamiento actualizado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public ResponseEntity<EntityModel<estacionamiento>> updateEstacionamiento(@PathVariable int id, @RequestBody estacionamiento estacionamiento) {
        estacionamiento.setId(id);
        estacionamiento updatedEstacionamiento = estacionamientoService.guardar(estacionamiento);
        return ResponseEntity
                .ok(assembler.toModel(updatedEstacionamiento));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar estacionamiento", description = "Elimina un estacionamiento por su ID") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Estacionamiento eliminado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public ResponseEntity<?> deleteVehiculo(@PathVariable int id) {
        estacionamientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
