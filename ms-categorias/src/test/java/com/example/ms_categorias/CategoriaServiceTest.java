package com.example.ms_categorias;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.ms_categorias.model.Categoria;
import com.example.ms_categorias.repository.CategoriaRepository;
import com.example.ms_categorias.service.CategoriaService;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CategoriaServiceTest {
    
    @Autowired
    private CategoriaService categoriaService;

    @MockitoBean
    private CategoriaRepository categoriaRepository;

    @Test
    public void testFindAll() {
        when(categoriaRepository.findAll()).thenReturn(List.of(new Categoria(1, "Comedia")));

        List<Categoria> categorias = categoriaService.findAll();

        assertNotNull(categorias);
        assertEquals(1, categorias.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Categoria categoria = new Categoria(id, "Comedia");

        when(categoriaRepository.findById(id)).thenReturn(Optional.of(categoria));

        Categoria found = categoriaService.findById(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
    }


    @Test
    public void testSave() {
        Categoria categoria = new Categoria(1, "Comedia");

        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria saved = categoriaService.saveCategoria(categoria);

        assertNotNull(saved);
        assertEquals("Comedia", saved.getNombre());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        when(categoriaRepository.existsById(id)).thenReturn(true);
        
        doNothing().when(categoriaRepository).deleteById(id);

        categoriaService.deleteById(id);

        verify(categoriaRepository, times(1)).deleteById(id);
    }


    @Test
    public void testUpdate(){
        Integer id = 1;
        Categoria categoriaElegida = new Categoria(id, "Comedia");
        Categoria UpdCategoria = new Categoria(id, "Comedia");

        when(categoriaRepository.findById(id)).thenReturn(Optional.of(categoriaElegida));

        when(categoriaRepository.save(categoriaElegida)).thenReturn(categoriaElegida);

        Categoria saved = categoriaService.update(id, UpdCategoria);
        
        assertNotNull(saved);
        assertEquals("Comedia", saved.getNombre());

    }

}
