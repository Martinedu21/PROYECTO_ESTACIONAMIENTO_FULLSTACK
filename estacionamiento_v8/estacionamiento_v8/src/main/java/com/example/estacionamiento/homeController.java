package com.example.estacionamiento;

import com.example.estacionamiento.model.estacionamiento;
import com.example.estacionamiento.model.ticket;
import com.example.estacionamiento.model.vehiculo;
import com.example.estacionamiento.service.estacionamientoService;
import com.example.estacionamiento.service.ticketService;
import com.example.estacionamiento.service.vehiculoService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class homeController {

    private final vehiculoService vservice;
    private final ticketService tservice;
    private final estacionamientoService eservice;
    int espacio_estacionamiento = 0;
    public homeController(vehiculoService vservice, ticketService tservice, estacionamientoService eservice) {
        this.vservice = vservice;
        this.tservice = tservice;
        this.eservice = eservice;
    }
    @PostMapping("/crear_ticket")
    public ResponseEntity<ticket> crear_ticket(vehiculo v){
        ticket t = new ticket();
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
        return ResponseEntity.status(HttpStatus.CREATED).body(tservice.guardar(t));
    }

    @PostMapping("/crear_estacionamiento")
    public ResponseEntity<estacionamiento> crear_estacionamiento(vehiculo v){
        estacionamiento e = new estacionamiento();
        espacio_estacionamiento ++;
        e.setNumeroEspacio("E"+espacio_estacionamiento);
        e.setPatente(v.getPatente());
        e.setDisponible(false);
        return ResponseEntity.status(HttpStatus.CREATED).body(eservice.guardar(e));
    }
    
    @PostMapping("/ingreso_vehiculo")
    public ResponseEntity<vehiculo> crear(@Valid @RequestBody vehiculo v) {
        crear_ticket(v);
        crear_estacionamiento(v);
        return ResponseEntity.status(HttpStatus.CREATED).body(vservice.guardar(v));
    }
}
