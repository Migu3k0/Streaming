package com.example.ms_suscripciones;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.ms_suscripciones.model.Suscripcion;
import com.example.ms_suscripciones.repository.SuscripcionRepository;
import com.example.ms_suscripciones.service.SuscripcionService;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class SuscripcionServiceTest {
    
    @Autowired
    private SuscripcionService suscripcionService;

    @MockitoBean
    private SuscripcionRepository suscripcionRepository;

    @Test
    public void testFindAll() {
        when(suscripcionRepository.findAll()).thenReturn(List.of(new Suscripcion(1, "Estandar", "02-01-2026", "02-02-2026", 7)));

        List<Suscripcion> suscripciones = suscripcionService.findAll();

        assertNotNull(suscripciones);
        assertEquals(1, suscripciones.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Suscripcion suscripcion = new Suscripcion(id, "Estandar", "02-01-2026", "02-02-2026", 7);

        when(suscripcionRepository.findById(id)).thenReturn(Optional.of(suscripcion));

        Suscripcion found = suscripcionService.findById(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
    }


    @Test
    public void testSave() {
        Suscripcion suscripcion = new Suscripcion(1, "Estandar", "02-01-2026", "02-02-2026", 7);

        when(suscripcionRepository.save(suscripcion)).thenReturn(suscripcion);

        Suscripcion saved = suscripcionService.saveSuscripcion(suscripcion);

        assertNotNull(saved);
        assertEquals("Estandar", saved.getNombre());
        assertEquals("02-01-2026", saved.getFechaInicio());
        assertEquals("02-02-2026", saved.getFechaTermino());
        assertEquals(7, saved.getPrecio());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        when(suscripcionRepository.existsById(id)).thenReturn(true);
        
        doNothing().when(suscripcionRepository).deleteById(id);

        suscripcionService.deleteById(id);

        verify(suscripcionRepository, times(1)).deleteById(id);
    }


    @Test
    public void testUpdate(){
        Integer id = 1;
        Suscripcion suscripcionElegida = new Suscripcion(id, "Estandar", "02-01-2026", "02-02-2026", 7);
        Suscripcion UpdSuscripcion= new Suscripcion(id, "Premium", "02-01-2026", "02-01-2027", 50);

        when(suscripcionRepository.findById(id)).thenReturn(Optional.of(suscripcionElegida));

        when(suscripcionRepository.save(suscripcionElegida)).thenReturn(suscripcionElegida);

        Suscripcion saved = suscripcionService.update(id, UpdSuscripcion);
        
        assertNotNull(saved);
        assertEquals("Premium", saved.getNombre());
        assertEquals("02-01-2026", saved.getFechaInicio());
        assertEquals("02-01-2027", saved.getFechaTermino());
        assertEquals(50, saved.getPrecio());

    }

}
