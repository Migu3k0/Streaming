package com.example.ms_historial.service;

import com.example.ms_historial.Client.SerieFeignClient;
import com.example.ms_historial.model.DTO.SerieDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_historial.model.Historial;
import com.example.ms_historial.repository.HistorialRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HistorialService {
    
    @Autowired
    private HistorialRepository repository;

    @Autowired
    private SerieFeignClient serieClient;


    public Historial saveHistorial(Historial hist){
        return repository.save(hist);
    }

    public List<Historial> findAll() {
        return repository.findAll();
    }


    public String deleteById(Integer id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return "Historial eliminada exitosamente";
        }
        return "Historial no disponible";
    }

    
    public Map<String, Object> findHistorial(Integer id) {
        Historial historito = repository.findById(id).orElse(null);
        Map<String, Object> respuesta = new HashMap<>();

        if (historito != null) {

            List<SerieDTO> seriesDetalle = historito.getSeriesIds().stream()
                    .map(serieId -> serieClient.obtenerSeriePorId(serieId))
                    .collect(Collectors.toList());

            respuesta.put("id", historito.getId());
            respuesta.put("seriesFavoritas", seriesDetalle);
        }
        return respuesta;
    }
    

}
