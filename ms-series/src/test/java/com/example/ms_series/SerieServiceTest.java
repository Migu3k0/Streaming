package com.example.ms_series;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.ms_series.Client.CategoriaFeignClient;
import com.example.ms_series.Client.EpisodioFeignClient;
import com.example.ms_series.Client.EstudioFeignClient;
import com.example.ms_series.model.Serie;
import com.example.ms_series.model.DTO.CategoriaDTO;
import com.example.ms_series.model.DTO.EpisodioDTO;
import com.example.ms_series.model.DTO.EstudioDTO;
import com.example.ms_series.repository.SerieRepository;
import com.example.ms_series.service.SerieService;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@SpringBootTest
public class SerieServiceTest {
    
    @Autowired
    private SerieService serieService;

    @MockitoBean
    private SerieRepository serieRepository;

    @MockitoBean
    private CategoriaFeignClient categoriaFeignClient;
    @MockitoBean
    private EpisodioFeignClient episodioFeignClient;
    @MockitoBean
    private EstudioFeignClient estudioFeignClient;

    @Test
    public void testFindAll() {
        when(serieRepository.findAll()).thenReturn(List.of(new Serie(1, "Bocchi The Rock", 1, List.of(1), List.of(1), List.of(1))));

        List<Serie> series = serieService.findAll();

        assertNotNull(series);
        assertEquals(1, series.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Integer idCategoria = 1;
        Integer idEpisodio = 1;
        Integer idEstudio = 1;
        Serie serie = new Serie(id, "Bocchi The Rock", 1, List.of(idCategoria), List.of(idEpisodio), List.of(idEstudio));

        CategoriaDTO categoria = new CategoriaDTO(idCategoria, "Comedia");
        EpisodioDTO episodio = new EpisodioDTO(idEpisodio, "Debut de la Banda");
        EstudioDTO estudio = new EstudioDTO(idEstudio, "CloverWorks");

        when(serieRepository.findById(id)).thenReturn(Optional.of(serie));
        when(categoriaFeignClient.obtenerCategoriaPorId(idCategoria)).thenReturn(categoria);
        when(episodioFeignClient.obtenerEpisodioPorId(idEpisodio)).thenReturn(episodio);
        when(estudioFeignClient.obtenerEstudioPorId(idEstudio)).thenReturn(estudio);

        Map<String, Object> found = serieService.findSerieCompleta(id);

        assertNotNull(found);
        assertEquals(id, found.get("id"));
    }


    @Test
    public void testSave() {
        Integer id = 1;

        Serie serie = new Serie(id, "Bocchi The Rock", 1, List.of(1), List.of(1), List.of(1));

        when(serieRepository.save(serie)).thenReturn(serie);

        Serie saved = serieService.saveSerie(serie);

        assertNotNull(saved);
        assertEquals("Bocchi The Rock", saved.getTitulo());
        assertEquals(1, saved.getTemporadas());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        when(serieRepository.existsById(id)).thenReturn(true);

        doNothing().when(serieRepository).deleteById(id);

        serieService.deleteById(id);

        verify(serieRepository, times(1)).deleteById(id);
    }


    @Test
    public void testUpdate(){
        Integer id = 1;
        Integer idCategoria = 1;
        Integer idEpisodio = 1;
        Integer idEstudio = 1;

        Serie serieElegida = new Serie(id, "Bocchi The Rock", 1, List.of(idCategoria), List.of(idEpisodio), List.of(idEstudio));

        Serie UpdSerie = new Serie(id, "Bocchi The Rock", 2, List.of(idCategoria), List.of(idEpisodio), List.of(idEstudio));

        when(serieRepository.findById(id)).thenReturn(Optional.of(serieElegida));

        when(serieRepository.save(serieElegida)).thenReturn(serieElegida);

        Serie saved = serieService.update(id, UpdSerie);
        
        assertNotNull(saved);
        assertEquals("Bocchi The Rock", saved.getTitulo());
        assertEquals(2, saved.getTemporadas());

    }

}
