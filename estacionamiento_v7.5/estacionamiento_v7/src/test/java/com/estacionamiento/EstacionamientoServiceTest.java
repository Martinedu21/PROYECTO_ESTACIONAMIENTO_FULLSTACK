package com.estacionamiento;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.estacionamiento.microservicioEstacionamiento.model.estacionamiento;
import com.example.estacionamiento.microservicioEstacionamiento.repository.estacionamientoRepository;
import com.example.estacionamiento.microservicioEstacionamiento.service.estacionamientoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class EstacionamientoServiceTest {

    // Inyecta el servicio de Carrera para ser probado.
    @Autowired
    private estacionamientoService estacionamientoService;

    // Crea un mock del repositorio de Carrera para simular su comportamiento.
    @MockBean
    private estacionamientoRepository estacionamientoRepository;

    @Test
    public void testFindAll() {
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(estacionamientoRepository.findAll()).thenReturn(List.of(new estacionamiento("1", "A2","RX1323",1)));

        // Llama al método findAll() del servicio.
        List<estacionamiento> estacionamientos = estacionamientoService.listar();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(estacionamientos);
        assertEquals(1, estacionamientos.size());
    }

    @Test
    public void testFindByCodigo() {
        String codigo = "1";
        estacionamiento estacionamiento = new estacionamiento(codigo,"A2","RX1323",1);

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Carrera opcional.
        when(estacionamientoRepository.findById(codigo)).thenReturn(Optional.of(estacionamiento));

        // Llama al método findByCodigo() del servicio.
        estacionamiento found = estacionamientoService.obtener(codigo);

        // Verifica que la Carrera devuelta no sea nula y que su código coincida con el código esperado.
        assertNotNull(found);
        assertEquals(codigo, found.getId());
    }

    @Test
    public void testSave() {
        Carrera carrera = new Carrera("1", "Ingeniería");

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(carreraRepository.save(carrera)).thenReturn(carrera);

        // Llama al método save() del servicio.
        Carrera saved = carreraService.save(carrera);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals("Ingeniería", saved.getNombre());
    }

    @Test
    public void testDeleteByCodigo() {
        String codigo = "1";

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(carreraRepository).deleteById(codigo);

        // Llama al método deleteByCodigo() del servicio.
        carreraService.deleteByCodigo(codigo);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(carreraRepository, times(1)).deleteById(codigo);
    }



    @Test
    void testFindAll_okConUnElemento() {
        // 1 elemento → size será 1
        when(carreraRepository.findAll())
                .thenReturn(List.of(new Carrera("2", "Analista")));

        List<Carrera> carreras = carreraService.findAll();

        assertEquals(1, carreras.size());      // ✅ ahora coincide
    }

}
