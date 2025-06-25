package com.example.estacionamiento;

import com.example.estacionamiento.model.*;
import com.example.estacionamiento.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private estacionamientoRepository estacionamientoRepository;
    @Autowired
    private ticketRepository ticketRepository;
    @Autowired
    private vehiculoRepository vehiculoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Generar vehiculo
        for (int i = 0; i < 50; i++) {
            vehiculo vehiculo = new vehiculo();
            vehiculo.setMarca(faker.vehicle().make());
            vehiculo.setModelo(faker.vehicle().model());
            vehiculo.setPatente(faker.vehicle().licensePlate());
            vehiculo.setTipo(faker.vehicle().carType());
            vehiculoRepository.save(vehiculo);
        }

        // Generar estacionamientos
        for (int i = 0; i < 3; i++) {
            estacionamiento estacionamiento = new estacionamiento();
            estacionamiento.setNumeroEspacio(faker.code().asin());
            estacionamiento.setPatente(faker.vehicle().licensePlate());
            estacionamiento.setDisponible(true);
            estacionamientoRepository.save(estacionamiento);
        }

        List<vehiculo> vehiculos = vehiculoRepository.findAll();
        // Generar ticket
        for (int i = 0; i < 5; i++) {
            ticket ticket = new ticket();
            ticket.setFecha(LocalDateTime.now());
            ticket.setPatente(vehiculos.get(random.nextInt(vehiculos.size())).getPatente());
            ticket.setTipo(vehiculos.get(random.nextInt(vehiculos.size())).getTipo());
            ticket.setValor((faker.options().option(1000,2000,3000)));
            ticketRepository.save(ticket);
        }

        
    }
}