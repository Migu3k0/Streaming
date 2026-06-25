package com.example.ms_perfil;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.ms_perfil.Client.UsuarioFeignClient;
import com.example.ms_perfil.model.Perfil;
import com.example.ms_perfil.model.DTO.UsuarioDTO;
import com.example.ms_perfil.repository.PerfilRepository;
import com.example.ms_perfil.service.PerfilService;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@SpringBootTest
public class PerfilServiceTest {
    
    @Autowired
    private PerfilService perfilService;

    @MockitoBean
    private PerfilRepository perfilRepository;

    @MockitoBean
    private UsuarioFeignClient usuarioFeignClient;

    @Test
    public void testFindAll() {
        when(perfilRepository.findAll()).thenReturn(List.of(new Perfil(1, "Aki Hayakawa", List.of(1))));

        List<Perfil> perfiles = perfilService.findAll();

        assertNotNull(perfiles);
        assertEquals(1, perfiles.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Integer idUsuario = 1;
        Perfil perfil = new Perfil(id, "Aki Hayakawa", List.of(idUsuario));

        UsuarioDTO usuario = new UsuarioDTO(idUsuario, "Himeno");

        when(perfilRepository.findById(id)).thenReturn(Optional.of(perfil));
        when(usuarioFeignClient.obtenerUsuarioPorId(idUsuario)).thenReturn(usuario);

        Map<String, Object> found = perfilService.findPerfilCompleto(id);

        assertNotNull(found);
        assertEquals(id, found.get("id"));
    }


    @Test
    public void testSave() {
        Integer id = 1;
        Integer idUsuario = 1;
        Perfil perfil = new Perfil(id, "Aki Hayakawa", List.of(idUsuario));

        when(perfilRepository.save(perfil)).thenReturn(perfil);

        Perfil saved = perfilService.savePerfil(perfil);

        assertNotNull(saved);
        assertEquals("¡Apresurate Goku!", saved.getNombre());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        when(perfilRepository.existsById(id)).thenReturn(true);

        doNothing().when(perfilRepository).deleteById(id);

        perfilService.deleteById(id);

        verify(perfilRepository, times(1)).deleteById(id);
    }


    @Test
    public void testUpdate(){
        Integer id = 1;
        Integer idUsuario = 1;
        Perfil perfilElegido = new Perfil(id, "Aki Hayakawa", List.of(idUsuario));

        Perfil UpdPerfil = new Perfil(id, "Aki Hayakawa and Denji", List.of(idUsuario));

        when(perfilRepository.findById(id)).thenReturn(Optional.of(perfilElegido));

        when(perfilRepository.save(perfilElegido)).thenReturn(perfilElegido);

        Perfil saved = perfilService.update(id, UpdPerfil);
        
        assertNotNull(saved);
        assertEquals("Aki Hayawaka and Denji", saved.getNombre());

    }

}
