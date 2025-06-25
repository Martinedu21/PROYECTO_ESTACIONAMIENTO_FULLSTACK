package com.example.estacionamiento.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.example.estacionamiento.controller.estacionamientoControllerV2;
import com.example.estacionamiento.model.estacionamiento;

@Component
public class EstacionamientoModelAssembler implements RepresentationModelAssembler<estacionamiento, EntityModel<estacionamiento>> {

    @SuppressWarnings("null")
    @Override

    public EntityModel<estacionamiento> toModel(estacionamiento estacionamiento) {
        return EntityModel.of(estacionamiento,
                linkTo(methodOn(estacionamientoControllerV2.class).getEstacionamientoById(estacionamiento.getId())).withSelfRel(),
                linkTo(methodOn(estacionamientoControllerV2.class).getAllEstacionamientos()).withRel("estacionamientos"));
    }
}