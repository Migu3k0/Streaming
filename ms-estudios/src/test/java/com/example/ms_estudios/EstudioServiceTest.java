package com.example.ms_estudios;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.ms_estudios.model.Estudio;
import com.example.ms_estudios.repository.EstudioRepository;
import com.example.ms_estudios.service.EstudioService;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EstudioServiceTest {
    
    @Autowired
    private EstudioService estudioService;

    @MockitoBean
    private EstudioRepository estudioRepository;

    @Test
    public void testFindAll() {
        when(estudioRepository.findAll()).thenReturn(List.of(new Estudio(1, "A24", 2012, "New York")));

        List<Estudio> estudios = estudioService.findAll();

        assertNotNull(estudios);
        assertEquals(1, estudios.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Estudio estudio = new Estudio(id, "A24", 2012, "New York");

        when(estudioRepository.findById(id)).thenReturn(Optional.of(estudio));

        Estudio found = estudioService.findById(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
    }


    @Test
    public void testSave() {

        Estudio estudio = new Estudio(1, "A24", 2012, "New York");

        when(estudioRepository.save(estudio)).thenReturn(estudio);

        Estudio saved = estudioService.saveEstudio(estudio);

        assertNotNull(saved);
        assertEquals("A24", saved.getNombre());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        when(estudioRepository.existsById(id)).thenReturn(true);

        doNothing().when(estudioRepository).deleteById(id);

        estudioService.deleteById(id);

        verify(estudioRepository, times(1)).deleteById(id);
    }


    @Test
    public void testUpdate(){
        Integer id = 1;
        Estudio estudioElegido = new Estudio(id, "A24", 2012, "New York");
        Estudio UpdEstudio = new Estudio(id, "A24", 2012, "New York");

        when(estudioRepository.findById(id)).thenReturn(Optional.of(estudioElegido));

        when(estudioRepository.save(estudioElegido)).thenReturn(estudioElegido);

        Estudio saved = estudioService.update(id, UpdEstudio);
        
        assertNotNull(saved);
        assertEquals("A24", saved.getNombre());

    }

}
