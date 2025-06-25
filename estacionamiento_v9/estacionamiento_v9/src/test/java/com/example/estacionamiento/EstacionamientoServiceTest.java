package com.example.estacionamiento;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.estacionamiento.model.estacionamiento;
import com.example.estacionamiento.model.vehiculo;
import com.example.estacionamiento.repository.estacionamientoRepository;
import com.example.estacionamiento.service.estacionamientoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
        estacionamiento estacionamiento = crear_estacionamiento(new vehiculo(1,"Lancia","Picanto","qbk-1148","Passenger Van"));
        when(estacionamientoRepository.findAll()).thenReturn(List.of(estacionamiento));

        List<estacionamiento> estacionamientos = estacionamientoService.listar();
        assertNotNull(estacionamientos);
        assertEquals(1, estacionamientos.size());
        assertEquals(estacionamiento.getId(), estacionamientos.get(0).getId());
    }

    @Test
    public void testFindById() {
        estacionamiento estacionamiento = crear_estacionamiento(new vehiculo(1,"Lancia","Picanto","qbk-1148","Passenger Van"));
        when(estacionamientoRepository.findById(1)).thenReturn(Optional.of(estacionamiento));
        estacionamiento found = estacionamientoService.obtener(1);
        assertNotNull(found);
        assertEquals(1, found.getId());
    }

    @Test
    public void testSave() {
        estacionamiento estacionamiento = crear_estacionamiento(new vehiculo(1,"Lancia","Picanto","qbk-1148","Passenger Van"));
        when(estacionamientoRepository.save(estacionamiento)).thenReturn(estacionamiento);

        estacionamiento saved = estacionamientoService.guardar(estacionamiento);
        assertNotNull(saved);
        assertEquals("qbk-1148", saved.getPatente());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(estacionamientoRepository).deleteById(id);

        estacionamientoService.eliminar(id);
        verify(estacionamientoRepository, times(1)).deleteById(id);
    }

    public estacionamiento crear_estacionamiento(vehiculo v){
        estacionamiento e = new estacionamiento();
        e.setId(1);
        e.setNumeroEspacio("E"+ 1);
        e.setPatente(v.getPatente());
        e.setDisponible(false);
        return e;
    }
}
