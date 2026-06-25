package com.example.ms_usuario;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.ms_usuario.Client.PerfilFeignClient;
import com.example.ms_usuario.model.Usuario;
import com.example.ms_usuario.model.DTO.PerfilDTO;
import com.example.ms_usuario.repository.UsuarioRepository;
import com.example.ms_usuario.service.UsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@SpringBootTest
public class UsuarioServiceTest {
    
    @Autowired
    private UsuarioService usuarioService;

    @MockitoBean
    private UsuarioRepository usuarioRepository;

    @MockitoBean
    private PerfilFeignClient perfilFeignClient;

    @Test
    public void testFindAll() {
        when(usuarioRepository.findAll()).thenReturn(List.of(new Usuario(1, "Himeno", "himenoV03@gmail.com", "Easy Revenge!", List.of(1))));

        List<Usuario> usuarios = usuarioService.findAll();

        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Integer idPerfil = 1;
        Usuario usuario = new Usuario(id, "Himeno", "himenoV03@gmail.com", "Easy Revenge!", List.of(idPerfil));

        PerfilDTO perfil = new PerfilDTO(idPerfil, "Dragon Ball Z");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        when(perfilFeignClient.obtenerPerfilPorId(idPerfil)).thenReturn(perfil);

        Map<String, Object> found = usuarioService.findUsuarioCompleto(id);

        assertNotNull(found);
        assertEquals(id, found.get("id"));
    }


    @Test
    public void testSave() {
        Integer id = 1;
        Integer idPerfil = 1;
        Usuario usuario = new Usuario(id, "Himeno", "himenoV03@gmail.com", "Easy Revenge!", List.of(idPerfil));

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario saved = usuarioService.saveUsuario(usuario);

        assertNotNull(saved);
        assertEquals("Himeno", saved.getNombre());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        when(usuarioRepository.existsById(id)).thenReturn(true);

        doNothing().when(usuarioRepository).deleteById(id);

        usuarioService.deleteById(id);

        verify(usuarioRepository, times(1)).deleteById(id);
    }


    @Test
    public void testUpdate(){
        Integer id = 1;
        Integer idPerfil = 1;
        Usuario usuarioElegido = new Usuario(id, "Himeno", "himenoV03@gmail.com", "Easy Revenge!", List.of(idPerfil));

        Usuario UpdUsuario = new Usuario(id, "Himeno", "himenoV03@gmail.com", "Easy Revenge!!", List.of(idPerfil));

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioElegido));

        when(usuarioRepository.save(usuarioElegido)).thenReturn(usuarioElegido);

        Usuario saved = usuarioService.update(id, UpdUsuario);
        
        assertNotNull(saved);
        assertEquals("Himeno", saved.getNombre());
        assertEquals("himenoV03@gmail.com", saved.getEmail());
        assertEquals("Easy Revenge!!", saved.getContrasenia());

    }

}
