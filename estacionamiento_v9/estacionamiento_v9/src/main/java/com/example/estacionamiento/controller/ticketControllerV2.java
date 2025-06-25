package com.example.estacionamiento.controller;

import com.example.estacionamiento.assemblers.TicketModelAssembler;
import com.example.estacionamiento.model.ticket;
import com.example.estacionamiento.service.ticketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/tickets")
public class ticketControllerV2 {

    @Autowired
    private ticketService ticketService;

    @Autowired
    private TicketModelAssembler assembler;

    @GetMapping(value = "/patente/{patente}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar por patente", description = "Trae un ticket por su patente") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ticket encontrado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public CollectionModel<EntityModel<ticket>> getTicketByPatente(@PathVariable String patente) {
        List<EntityModel<ticket>> tickets = ticketService.findByPatente(patente).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tickets,
                linkTo(methodOn(ticketControllerV2.class).getTicketByPatente(patente)).withSelfRel());
    }

    @GetMapping(value = "/fecha/{fecha}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar por fecha", description = "Trae un ticket por su fecha") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ticket encontrado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public CollectionModel<EntityModel<ticket>> getTicketsByFecha(@PathVariable Date fecha) {
        List<EntityModel<ticket>> tickets = ticketService.findByFecha(fecha).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tickets,
                linkTo(methodOn(ticketControllerV2.class).getTicketsByFecha(fecha)).withSelfRel());
    }

    @GetMapping(value = "/tipo/{tipo}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar por tipo", description = "Trae un ticket por su tipo") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ticket encontrado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public CollectionModel<EntityModel<ticket>> getTicketByTipo(@PathVariable String tipo) {
        List<EntityModel<ticket>> tickets = ticketService.findByTipo(tipo).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tickets,
                linkTo(methodOn(ticketControllerV2.class).getTicketByTipo(tipo)).withSelfRel());
    }

    @GetMapping(value = "/valor/{valor}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar por valor", description = "Trae un ticket por su valor") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ticket encontrado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public CollectionModel<EntityModel<ticket>> getTicketsByValor(@PathVariable float valor) {
        List<EntityModel<ticket>> tickets = ticketService.findByValor(valor).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tickets,
                linkTo(methodOn(ticketControllerV2.class).getTicketsByValor(valor)).withSelfRel());
    }


    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Traer todos los tickets", description = "Lista todos los tickets") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Tickets listados!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public CollectionModel<EntityModel<ticket>> getAllTickets() {
        List<EntityModel<ticket>> tickets = ticketService.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tickets,
                linkTo(methodOn(ticketControllerV2.class).getAllTickets()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener ticket por ID", description = "Trae un ticket por su ID") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ticket encontrado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public EntityModel<ticket> getTicketById(@PathVariable Integer id) {
        ticket ticket = ticketService.obtener(id);
        return assembler.toModel(ticket);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear ticket", description = "Crea un nuevo ticket") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ticket creado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public ResponseEntity<EntityModel<ticket>> createTicket(@RequestBody ticket ticket) {
        ticket newTicket = ticketService.guardar(ticket);
        return ResponseEntity
                .created(linkTo(methodOn(ticketControllerV2.class).getTicketById(newTicket.getId())).toUri())
                .body(assembler.toModel(newTicket));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar ticket", description = "Actualiza un ticket por su ID") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ticket actualizado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public ResponseEntity<EntityModel<ticket>> updateReserva(@PathVariable Integer id, @RequestBody ticket ticket) {
        ticket.setId(id);
        ticket updatedTicket = ticketService.guardar(ticket);
        return ResponseEntity.ok(assembler.toModel(updatedTicket));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar ticket", description = "Elimina un ticket por su ID") 
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ticket eliminado!"), 
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno") 
    })
    public ResponseEntity<?> deleteTicket(@PathVariable Integer id) {
        ticketService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
