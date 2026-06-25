package com.example.ms_peliculas.service;

import com.example.ms_peliculas.Client.*;
import com.example.ms_peliculas.model.DTO.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_peliculas.model.Pelicula;
import com.example.ms_peliculas.repository.PeliculaRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PeliculaService {
    
    @Autowired
    private PeliculaRepository repository;

     @Autowired
    private CategoriaFeignClient categoriaClient;

     @Autowired
    private EstudioFeignClient estudioClient;

    public Pelicula findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Pelicula savePelicula(Pelicula peli){
        return repository.save(peli);
    }

    public List<Pelicula> findAll() {
        return repository.findAll();
    }


    public String deleteById(Integer id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return "Pelicula eliminada exitosamente";
        }
        return "Pelicula no disponible";
    }

    public Pelicula update(Integer id, Pelicula updPelicula) {

        Pelicula PeliculaElegida = repository.findById(id).orElse(null);

        if (PeliculaElegida != null) {

            PeliculaElegida.setTitulo(updPelicula.getTitulo());
            PeliculaElegida.setDuracion(updPelicula.getDuracion());

            return repository.save(PeliculaElegida);
        }

        return null;
    }

    public Map<String, Object> findPeliculaCompleta(Integer id) {
        Pelicula pelicula = repository.findById(id).orElse(null);
        Map<String, Object> respuesta = new HashMap<>();

        if (pelicula != null) {

            List<CategoriaDTO> categoriasDetalle = pelicula.getCategoriasIds().stream()
                    .map(peliculaId -> categoriaClient.obtenerCategoriaPorId(peliculaId))
                    .collect(Collectors.toList());

            List<EstudioDTO> estudiosDetalle = pelicula.getEstudiosIds().stream()
                    .map(peliculaId -> estudioClient.obtenerEstudioPorId(peliculaId))
                    .collect(Collectors.toList());

            respuesta.put("id", pelicula.getId());
            respuesta.put("titulo", pelicula.getTitulo());
            respuesta.put("duracion", pelicula.getDuracion());
            respuesta.put("categoria", categoriasDetalle);
            respuesta.put("estudio", estudiosDetalle);
        }
        return respuesta;
    }

}
