package com.example.ms_favoritos;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.ms_favoritos.Client.SerieFeignClient;
import com.example.ms_favoritos.model.Favorito;
import com.example.ms_favoritos.model.DTO.SerieDTO;
import com.example.ms_favoritos.repository.FavoritoRepository;
import com.example.ms_favoritos.service.FavoritoService;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@SpringBootTest
public class FavoritoServiceTest {
    
    @Autowired
    private FavoritoService favoritoService;

    @MockitoBean
    private FavoritoRepository favoritoRepository;

    @MockitoBean
    private SerieFeignClient serieFeignClient;

    @Test
    public void testFindAll() {
        when(favoritoRepository.findAll()).thenReturn(List.of(new Favorito(1, List.of(1))));

        List<Favorito> favoritos = favoritoService.findAll();

        assertNotNull(favoritos);
        assertEquals(1, favoritos.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Integer idSerie = 1;
        Favorito favorito = new Favorito(id, List.of(idSerie));

        SerieDTO serie = new SerieDTO(idSerie, "Dragon Ball Z");

        when(favoritoRepository.findById(id)).thenReturn(Optional.of(favorito));
        when(serieFeignClient.obtenerSeriePorId(idSerie)).thenReturn(serie);

        Map<String, Object> found = favoritoService.findFavorito(id);

        assertNotNull(found);
        assertEquals(id, found.get("id"));
    }


    @Test
    public void testSave() {
        Integer id = 1;
        Integer idSerie = 1;
        Favorito favorito = new Favorito(id, List.of(idSerie));

        when(favoritoRepository.save(favorito)).thenReturn(favorito);

        Favorito saved = favoritoService.saveFavorito(favorito);

        assertNotNull(saved);
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        when(favoritoRepository.existsById(id)).thenReturn(true);

        doNothing().when(favoritoRepository).deleteById(id);

        favoritoService.deleteById(id);

        verify(favoritoRepository, times(1)).deleteById(id);
    }


    @Test
    public void testUpdate(){
        Integer id = 1;
        Integer idSerie = 1;
        Favorito favoritoElegido = new Favorito(id, List.of(idSerie));

        Favorito UpdFavorito = new Favorito(id, List.of(idSerie));

        when(favoritoRepository.findById(id)).thenReturn(Optional.of(favoritoElegido));

        when(favoritoRepository.save(favoritoElegido)).thenReturn(favoritoElegido);

    }

}
