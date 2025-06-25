package com.example.estacionamiento;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.estacionamiento.model.ticket;
import com.example.estacionamiento.model.vehiculo;
import com.example.estacionamiento.repository.ticketRepository;
import com.example.estacionamiento.service.ticketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class TicketServiceTest {

    @Autowired
    private ticketService ticketService;

    @MockBean
    private ticketRepository ticketRepository;

    @Test
    public void testFindAll() {
        ticket ticket = crear_ticket(new vehiculo(1,"Lancia","Picanto","qbk-1148","Passenger Van"));
        when(ticketRepository.findAll()).thenReturn(List.of(ticket));

        List<ticket> tickets = ticketService.listar();
        assertNotNull(tickets);
        assertEquals(1, tickets.size());
        assertEquals(ticket.getId(), tickets.get(0).getId());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        ticket ticket = crear_ticket(new vehiculo(1,"Lancia","Picanto","qbk-1148","Passenger Van"));
        when(ticketRepository.findById(id)).thenReturn(Optional.of(ticket));
        ticket found = ticketService.obtener(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        ticket ticket = crear_ticket(new vehiculo(1,"Lancia","Picanto","qbk-1148","Passenger Van"));
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        ticket saved = ticketService.guardar(ticket);
        assertNotNull(saved);
        assertEquals("qbk-1148", saved.getPatente());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(ticketRepository).deleteById(id);

        ticketService.eliminar(id);
        verify(ticketRepository, times(1)).deleteById(id);
    }

    public ticket crear_ticket(vehiculo v){
        ticket t = new ticket();
        t.setId(1);
        float valor = 0;
        LocalDateTime fecha = LocalDateTime.now();
        switch (v.getTipo()) {
            case "Moto":
                valor = 1000;
                break;
            case "Sedan":
                valor = 2000;
                break;
            case "Camioneta":
                valor = 3000;
                break;
            default:
                valor = 5000;
                break;
        }
        t.setTipo(v.getTipo());
        t.setValor(valor);
        t.setFecha(fecha);
        t.setPatente(v.getPatente());
        return t;
    }
}
