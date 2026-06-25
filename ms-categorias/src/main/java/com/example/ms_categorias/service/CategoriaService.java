package com.example.ms_categorias.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_categorias.model.Categoria;
import com.example.ms_categorias.repository.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository repository;

    public Categoria findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Categoria saveCategoria(Categoria cate){
        return repository.save(cate);
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }


    public String deleteById(Integer id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return "Categoria eliminada exitosamente";
        }
        return "Categoria no dispoible";
    }

    public Categoria update(Integer id, Categoria updCategoria) {

        Categoria CategoriaElegida = repository.findById(id).orElse(null);

        if (CategoriaElegida != null) {

            CategoriaElegida.setNombre(updCategoria.getNombre());

            return repository.save(CategoriaElegida);
        }

        return null;
    }

}
