package com.example.estacionamiento.assemblers;

import com.example.estacionamiento.controller.ticketControllerV2;
import com.example.estacionamiento.model.ticket;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TicketModelAssembler implements RepresentationModelAssembler<ticket, EntityModel<ticket>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<ticket> toModel(ticket ticket) {
        return EntityModel.of(ticket,
                linkTo(methodOn(ticketControllerV2.class).getTicketById(ticket.getId())).withSelfRel(),
                linkTo(methodOn(ticketControllerV2.class).getAllTickets()).withRel("tickets"));
    }
}