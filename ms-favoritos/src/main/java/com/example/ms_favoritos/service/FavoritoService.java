package com.example.ms_favoritos.service;

import com.example.ms_favoritos.Client.SerieFeignClient;
import com.example.ms_favoritos.model.DTO.SerieDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_favoritos.model.Favorito;
import com.example.ms_favoritos.repository.FavoritoRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FavoritoService {
    
    @Autowired
    private FavoritoRepository repository;

    @Autowired
    private SerieFeignClient serieClient;


    public Favorito saveFavorito(Favorito fav){
        return repository.save(fav);
    }

    public List<Favorito> findAll() {
        return repository.findAll();
    }


    public String deleteById(Integer id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return "Favorito eliminada exitosamente";
        }
        return "Favorito no dispoible";
    }

    
    public Map<String, Object> findFavorito(Integer id) {
        Favorito favorito = repository.findById(id).orElse(null);
        Map<String, Object> respuesta = new HashMap<>();

        if (favorito != null) {

            List<SerieDTO> seriesDetalle = favorito.getSeriesIds().stream()
                    .map(serieId -> serieClient.obtenerSeriePorId(serieId))
                    .collect(Collectors.toList());

            respuesta.put("id", favorito.getId());
            respuesta.put("seriesFavoritas", seriesDetalle);
        }
        return respuesta;
    }
    

}
