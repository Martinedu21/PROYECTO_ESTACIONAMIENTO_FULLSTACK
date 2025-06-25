package com.example.estacionamiento.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.estacionamiento.controller.vehiculoControllerV2;
import com.example.estacionamiento.model.vehiculo;

@Component
public class VehiculoModelAssembler implements RepresentationModelAssembler<vehiculo, EntityModel<vehiculo>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<vehiculo> toModel(vehiculo vehiculo) {
        return EntityModel.of(vehiculo,
                linkTo(methodOn(vehiculoControllerV2.class).getVehiculoById(vehiculo.getId())).withSelfRel(),
                linkTo(methodOn(vehiculoControllerV2.class).getAllvehiculos()).withRel("vehiculos"));
    }
}