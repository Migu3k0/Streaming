package com.example.ms_suscripciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_suscripciones.model.Suscripcion;
import com.example.ms_suscripciones.repository.SuscripcionRepository;

import java.util.List;

@Service
public class SuscripcionService {
    
    @Autowired
    private SuscripcionRepository repository;

    public Suscripcion findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Suscripcion saveSuscripcion(Suscripcion sub){
        return repository.save(sub);
    }

    public List<Suscripcion> findAll() {
        return repository.findAll();
    }


    public String deleteById(Integer id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return "Suscripcion eliminada exitosamente";
        }
        return "Suscripcion no dispoible";
    }

    public Suscripcion update(Integer id, Suscripcion updSuscripcion) {

        Suscripcion SuscripcionElegida = repository.findById(id).orElse(null);

        if (SuscripcionElegida != null) {

            SuscripcionElegida.setNombre(updSuscripcion.getNombre());
            SuscripcionElegida.setFechaInicio(updSuscripcion.getFechaInicio());
            SuscripcionElegida.setFechaTermino(updSuscripcion.getFechaTermino());
            SuscripcionElegida.setPrecio(updSuscripcion.getPrecio());

            return repository.save(SuscripcionElegida);
        }

        return null;
    }

}
