package com.example.ms_episodios;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.ms_episodios.Client.SerieFeignClient;
import com.example.ms_episodios.model.Episodio;
import com.example.ms_episodios.model.DTO.SerieDTO;
import com.example.ms_episodios.repository.EpisodioRepository;
import com.example.ms_episodios.service.EpisodioService;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@SpringBootTest
public class EpisodioServiceTest {
    
    @Autowired
    private EpisodioService episodioService;

    @MockitoBean
    private EpisodioRepository episodioRepository;

    @MockitoBean
    private SerieFeignClient serieFeignClient;

    @Test
    public void testFindAll() {
        when(episodioRepository.findAll()).thenReturn(List.of(new Episodio(1, "¡Apresurate Goku!", 24, "20-08-97", List.of(1))));

        List<Episodio> episodios = episodioService.findAll();

        assertNotNull(episodios);
        assertEquals(1, episodios.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Integer idSerie = 1;
        Episodio episodio = new Episodio(id, "¡Apresurate Goku!", 24, "20-08-97", List.of(idSerie));

        SerieDTO serie = new SerieDTO(idSerie, "Dragon Ball Z");

        when(episodioRepository.findById(id)).thenReturn(Optional.of(episodio));
        when(serieFeignClient.obtenerSeriePorId(idSerie)).thenReturn(serie);

        Map<String, Object> found = episodioService.findEpisodioCompleto(id);

        assertNotNull(found);
        assertEquals(id, found.get("id"));
    }


    @Test
    public void testSave() {
        Integer id = 1;
        Integer idSerie = 1;
        Episodio episodio = new Episodio(id, "¡Apresurate Goku!", 24, "20-08-97", List.of(idSerie));

        when(episodioRepository.save(episodio)).thenReturn(episodio);

        Episodio saved = episodioService.saveEpisodio(episodio);

        assertNotNull(saved);
        assertEquals("¡Apresurate Goku!", saved.getTitulo());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        when(episodioRepository.existsById(id)).thenReturn(true);

        doNothing().when(episodioRepository).deleteById(id);

        episodioService.deleteById(id);

        verify(episodioRepository, times(1)).deleteById(id);
    }


    @Test
    public void testUpdate(){
        Integer id = 1;
        Integer idSerie = 1;
        Episodio episodioElegido = new Episodio(id, "¡Apresurate Goku!", 24, "20-08-97", List.of(idSerie));

        Episodio UpdEpisodio = new Episodio(id, "¡Apresurate Goku!", 24, "21-08-97", List.of(idSerie));

        when(episodioRepository.findById(id)).thenReturn(Optional.of(episodioElegido));

        when(episodioRepository.save(episodioElegido)).thenReturn(episodioElegido);

        Episodio saved = episodioService.update(id, UpdEpisodio);
        
        assertNotNull(saved);
        assertEquals("¡Apresurate Goku!", saved.getTitulo());
        assertEquals(24, saved.getDuracion());
        assertEquals("21-08-97", saved.getFechaSalida());

    }

}
