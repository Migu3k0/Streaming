package com.example.ms_peliculas;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.ms_peliculas.Client.CategoriaFeignClient;
import com.example.ms_peliculas.Client.EstudioFeignClient;
import com.example.ms_peliculas.model.Pelicula;
import com.example.ms_peliculas.model.DTO.CategoriaDTO;
import com.example.ms_peliculas.model.DTO.EstudioDTO;
import com.example.ms_peliculas.repository.PeliculaRepository;
import com.example.ms_peliculas.service.PeliculaService;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@SpringBootTest
public class PeliculaServiceTest {
    
    @Autowired
    private PeliculaService peliculaService;

    @MockitoBean
    private PeliculaRepository peliculaRepository;

    @MockitoBean
    private CategoriaFeignClient categoriaFeignClient;
    @MockitoBean
    private EstudioFeignClient estudioFeignClient;

    @Test
    public void testFindAll() {
        when(peliculaRepository.findAll()).thenReturn(List.of(new Pelicula(1, "Backrooms", (float) 140, List.of(1), List.of(1))));

        List<Pelicula> peliculas = peliculaService.findAll();

        assertNotNull(peliculas);
        assertEquals(1, peliculas.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Integer idCategoria = 1;
        Integer idEstudio = 1;
        Pelicula pelicula = new Pelicula(id, "Backrooms", (float) 140, List.of(idCategoria), List.of(idEstudio));

        CategoriaDTO categoria = new CategoriaDTO(idCategoria, "Terror");
        EstudioDTO estudio = new EstudioDTO(idEstudio, "A24");

        when(peliculaRepository.findById(id)).thenReturn(Optional.of(pelicula));
        when(categoriaFeignClient.obtenerCategoriaPorId(idCategoria)).thenReturn(categoria);
        when(estudioFeignClient.obtenerEstudioPorId(idEstudio)).thenReturn(estudio);

        Map<String, Object> found = peliculaService.findPeliculaCompleta(id);

        assertNotNull(found);
        assertEquals(id, found.get("id"));
    }


    @Test
    public void testSave() {
        Integer id = 1;

        Pelicula pelicula = new Pelicula(id, "Backrooms", (float) 140, List.of(1), List.of(1));

        when(peliculaRepository.save(pelicula)).thenReturn(pelicula);

        Pelicula saved = peliculaService.savePelicula(pelicula);

        assertNotNull(saved);
        assertEquals("Backrooms", saved.getTitulo());
        assertEquals(140, saved.getDuracion());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        when(peliculaRepository.existsById(id)).thenReturn(true);

        doNothing().when(peliculaRepository).deleteById(id);

        peliculaService.deleteById(id);

        verify(peliculaRepository, times(1)).deleteById(id);
    }


    @Test
    public void testUpdate(){
        Integer id = 1;
        Integer idCategoria = 1;
        Integer idEstudio = 1;

        Pelicula peliculaElegida = new Pelicula(id, "Backrooms", (float) 140, List.of(idCategoria), List.of(idEstudio));

        Pelicula UpdPelicula = new Pelicula(id, "Backrooms: Sin Salida", (float) 140, List.of(idCategoria), List.of(idEstudio));

        when(peliculaRepository.findById(id)).thenReturn(Optional.of(peliculaElegida));

        when(peliculaRepository.save(peliculaElegida)).thenReturn(peliculaElegida);

        Pelicula saved = peliculaService.update(id, UpdPelicula);
        
        assertNotNull(saved);
        assertEquals("Backrooms: Sin Salida", saved.getTitulo());
        assertEquals(140, saved.getDuracion());

    }

}
