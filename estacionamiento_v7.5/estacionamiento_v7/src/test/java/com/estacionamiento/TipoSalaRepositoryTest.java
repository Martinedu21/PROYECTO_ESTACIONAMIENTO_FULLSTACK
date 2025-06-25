package com.estacionamiento;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.TipoSala;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.TipoSalaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.annotation.Commit;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Commit
@ActiveProfiles("test")   //  ← Usará db_biblioteca_test
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TipoSalaRepositoryTest {

    @Autowired
    private TipoSalaRepository repo;

    @BeforeEach
    void seed() {
        // Insertamos un registro que la prueba pueda encontrar
        repo.save(new TipoSala(1, "Biography/Autobiography"));
    }

    @Test
    void findById_retornaRegistroReal() {
        Optional<TipoSala> result = repo.findById(1);

        assertTrue(result.isPresent());
        assertEquals("Biography/Autobiography", result.get().getNombre());
    }
}
