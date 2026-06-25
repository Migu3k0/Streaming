package com.example.ms_episodios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_episodios.Client.SerieFeignClient;
import com.example.ms_episodios.model.Episodio;
import com.example.ms_episodios.model.DTO.SerieDTO;
import com.example.ms_episodios.repository.EpisodioRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EpisodioService {
    
    @Autowired
    private EpisodioRepository repository;
    
    @Autowired
    private SerieFeignClient serieClient;


    public Map<String, Object> findEpisodioCompleto(Integer id) {
        Episodio episodio = repository.findById(id).orElse(null);
        Map<String, Object> respuesta = new HashMap<>();

        if (episodio != null) {

            List<SerieDTO> seriesDetalle = episodio.getSeriesIds().stream()
                    .map(serieId -> serieClient.obtenerSeriePorId(serieId))
                    .collect(Collectors.toList());

            respuesta.put("id", episodio.getId());
            respuesta.put("titulo", episodio.getTitulo());
            respuesta.put("duracion", episodio.getDuracion());
            respuesta.put("fechaSalida", episodio.getFechaSalida());
            respuesta.put("serie", seriesDetalle);
        }
        return respuesta;
    }

    public Episodio saveEpisodio(Episodio ep){
        return repository.save(ep);
    }

    public List<Episodio> findAll() {
        return repository.findAll();
    }


    public String deleteById(Integer id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return "Episodio eliminada exitosamente";
        }
        return "Episodio no dispoible";
    }

    public Episodio update(Integer id, Episodio updEpisodio) {

        Episodio EpisodioElegido = repository.findById(id).orElse(null);

        if (EpisodioElegido != null) {

            EpisodioElegido.setTitulo(updEpisodio.getTitulo());
            EpisodioElegido.setDuracion(updEpisodio.getDuracion());
            EpisodioElegido.setFechaSalida(updEpisodio.getFechaSalida());

            return repository.save(EpisodioElegido);
        }

        return null;
    }

}
