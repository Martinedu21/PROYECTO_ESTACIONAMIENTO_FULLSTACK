package com.example.estacionamiento;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.estacionamiento.model.vehiculo;
import com.example.estacionamiento.repository.vehiculoRepository;
import com.example.estacionamiento.service.vehiculoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class VehiculoServiceTest {

    @Autowired
    private vehiculoService vehiculoService;

    @MockBean
    private vehiculoRepository vehiculoRepository;

    @Test
    public void testFindAll() {
        when(vehiculoRepository.findAll()).thenReturn(List.of(new vehiculo(1,"Lancia","Picanto","qbk-1148","Passenger Van")));
        List<vehiculo> vehiculos = vehiculoService.listar();
        assertNotNull(vehiculos);
        assertEquals(1, vehiculos.size());
    }

    @Test
    public void testFindByCodigo() {
        Integer id = 1;
        vehiculo vehiculo = new vehiculo(id, "Lancia","Picanto","qbk-1148","Passenger Van");
        when(vehiculoRepository.findById(id)).thenReturn(Optional.of(vehiculo));
        vehiculo found = vehiculoService.obtener(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        vehiculo vehiculo = new vehiculo(1,"Lancia","Picanto","qbk-1148","Passenger Van");
        when(vehiculoRepository.save(vehiculo)).thenReturn(vehiculo);
        vehiculo saved = vehiculoService.guardar(vehiculo);
        assertNotNull(saved);
        assertEquals("qbk-1148", saved.getPatente());
    }

    @Test
    public void testDeleteByCodigo() {
        Integer id = 1;
        doNothing().when(vehiculoRepository).deleteById(id);
        vehiculoService.eliminar(id);
        verify(vehiculoRepository, times(1)).deleteById(id);
    }



    @Test
    void testFindAll_okConUnElemento() {
        when(vehiculoRepository.findAll())
                .thenReturn(List.of(new vehiculo(2,"Nio","Model X","sen-8973","Hatchback")));

        List<vehiculo> vehiculos = vehiculoService.listar();

        assertEquals(1, vehiculos.size()); 
    }

}
