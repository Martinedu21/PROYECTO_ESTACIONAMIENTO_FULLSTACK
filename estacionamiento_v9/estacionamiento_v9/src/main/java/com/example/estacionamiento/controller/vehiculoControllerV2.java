package com.example.estacionamiento.controller;

import com.example.estacionamiento.assemblers.VehiculoModelAssembler;
import com.example.estacionamiento.model.vehiculo;
import com.example.estacionamiento.service.vehiculoService;

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
@RequestMapping("/api/v2/vehiculos")
public class vehiculoControllerV2 {

    @Autowired
    private vehiculoService vehiculoService;

    @Autowired
    private VehiculoModelAssembler assembler;

    @GetMapping(value = "/marca/{marca}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar por marca", description = "Trae un vehiculo por su patente") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Vehiculo encontrado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public CollectionModel<EntityModel<vehiculo>> getVehiculoByMarca(@PathVariable String marca) {
        List<EntityModel<vehiculo>> vehiculos = vehiculoService.findByMarca(marca).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(vehiculos,
                linkTo(methodOn(vehiculoControllerV2.class).getVehiculoByMarca(marca)).withSelfRel());
    }

    @GetMapping(value = "/modelo/{modelo}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar por modelo", description = "Trae un vehiculo por su modelo") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Vehiculo encontrado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public CollectionModel<EntityModel<vehiculo>> getVehiculoByModelo(@PathVariable String modelo) {
        List<EntityModel<vehiculo>> vehiculos = vehiculoService.findByModelo(modelo).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(vehiculos,
                linkTo(methodOn(vehiculoControllerV2.class).getVehiculoByModelo(modelo)).withSelfRel());
    }

    @GetMapping(value = "/patente/{patente}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar por patente", description = "Trae un vehiculo por su patente") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Vehiculo encontrado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public CollectionModel<EntityModel<vehiculo>> getVehiculoByPatente(@PathVariable String patente) {
        List<EntityModel<vehiculo>> vehiculos = vehiculoService.findByPatente(patente).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(vehiculos,
                linkTo(methodOn(vehiculoControllerV2.class).getVehiculoByPatente(patente)).withSelfRel());
    }

    @GetMapping(value = "/tipo/{tipo}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar por tipo", description = "Trae un vehiculo por su tipo") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Vehiculo encontrado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public CollectionModel<EntityModel<vehiculo>> getVehiculoByTipo(@PathVariable String tipo) {
        List<EntityModel<vehiculo>> vehiculos = vehiculoService.findByTipo(tipo).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(vehiculos,
                linkTo(methodOn(vehiculoControllerV2.class).getVehiculoByTipo(tipo)).withSelfRel());
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Traer todos los vehiculos", description = "Lista todos los vehiculos") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Vehiculos listados!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public CollectionModel<EntityModel<vehiculo>> getAllvehiculos() {
        List<EntityModel<vehiculo>> vehiculos = vehiculoService.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(vehiculos,
                linkTo(methodOn(vehiculoControllerV2.class).getAllvehiculos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener vehiculo por ID", description = "Trae un vehiculo por su ID") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "vehiculo encontrado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public EntityModel<vehiculo> getVehiculoById(@PathVariable int id) {
        vehiculo vehiculo = vehiculoService.obtener(id);
        return assembler.toModel(vehiculo);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear vehiculo", description = "Crea un nuevo vehiculo") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Vehiculo creado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public ResponseEntity<EntityModel<vehiculo>> createVehiculo(@RequestBody vehiculo vehiculo) {
        vehiculo newVehiculo = vehiculoService.guardar(vehiculo);
        return ResponseEntity
                .created(linkTo(methodOn(vehiculoControllerV2.class).getVehiculoById(newVehiculo.getId())).toUri())
                .body(assembler.toModel(newVehiculo));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar vehiculo", description = "Actualiza un vehiculo por su ID") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Vehiculo actualizado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public ResponseEntity<EntityModel<vehiculo>> updateVehiculo(@PathVariable int id, @RequestBody vehiculo vehiculo) {
        vehiculo.setId(id);
        vehiculo updatedVehiculo = vehiculoService.guardar(vehiculo);
        return ResponseEntity
                .ok(assembler.toModel(updatedVehiculo));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar vehiculo", description = "Elimina un vehiculo por su ID") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Vehiculo eliminado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public ResponseEntity<?> deleteVehiculo(@PathVariable int id) {
        vehiculoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
