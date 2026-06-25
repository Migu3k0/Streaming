package com.example.ms_estudios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_estudios.model.Estudio;
import com.example.ms_estudios.repository.EstudioRepository;

import java.util.List;

@Service
public class EstudioService {
    
    @Autowired
    private EstudioRepository repository;

    public Estudio findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Estudio saveEstudio(Estudio ser){
        return repository.save(ser);
    }

    public List<Estudio> findAll() {
        return repository.findAll();
    }


    public String deleteById(Integer id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return "Estudio eliminada exitosamente";
        }
        return "Estudio no dispoible";
    }

    public Estudio update(Integer id, Estudio updEst) {

        Estudio EstudioElegido = repository.findById(id).orElse(null);

        if (EstudioElegido != null) {

            EstudioElegido.setNombre(updEst.getNombre());
            EstudioElegido.setFechaFundacion(updEst.getFechaFundacion());
            EstudioElegido.setDireccion(updEst.getDireccion());

            return repository.save(EstudioElegido);
        }

        return null;
    }

}
