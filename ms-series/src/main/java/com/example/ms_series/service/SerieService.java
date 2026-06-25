package com.example.ms_series.service;

import com.example.ms_series.Client.*;
import com.example.ms_series.model.DTO.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_series.model.Serie;
import com.example.ms_series.repository.SerieRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SerieService {
    
    @Autowired
    private SerieRepository repository;

     @Autowired
    private CategoriaFeignClient categoriaClient;

     @Autowired
    private EpisodioFeignClient episodioClient;

     @Autowired
    private EstudioFeignClient estudioClient;

    public Serie findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Serie saveSerie(Serie ser){
        return repository.save(ser);
    }

    public List<Serie> findAll() {
        return repository.findAll();
    }


    public String deleteById(Integer id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return "Serie eliminada exitosamente";
        }
        return "Serie no dispoible";
    }

    public Serie update(Integer id, Serie updSerie) {

        Serie SerieElegida = repository.findById(id).orElse(null);

        if (SerieElegida != null) {

            SerieElegida.setTitulo(updSerie.getTitulo());
            SerieElegida.setTemporadas(updSerie.getTemporadas());

            return repository.save(SerieElegida);
        }

        return null;
    }

    public Map<String, Object> findSerieCompleta(Integer id) {
        Serie serie = repository.findById(id).orElse(null);
        Map<String, Object> respuesta = new HashMap<>();

        if (serie != null) {

            List<CategoriaDTO> categoriasDetalle = serie.getCategoriasIds().stream()
                    .map(serieId -> categoriaClient.obtenerCategoriaPorId(serieId))
                    .collect(Collectors.toList());

            List<EpisodioDTO> episodiosDetalle = serie.getEpisodiosIds().stream()
                    .map(serieId -> episodioClient.obtenerEpisodioPorId(serieId))
                    .collect(Collectors.toList());

            List<EstudioDTO> estudiosDetalle = serie.getEstudiosIds().stream()
                    .map(serieId -> estudioClient.obtenerEstudioPorId(serieId))
                    .collect(Collectors.toList());

            respuesta.put("id", serie.getId());
            respuesta.put("titulo", serie.getTitulo());
            respuesta.put("temporadas", serie.getTemporadas());
            respuesta.put("episodios", episodiosDetalle);
            respuesta.put("categoria", categoriasDetalle);
            respuesta.put("estudio", estudiosDetalle);
        }
        return respuesta;
    }

}
