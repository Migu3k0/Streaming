package com.example.ms_categorias.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.example.ms_categorias.controller.CategoriaControllerV2;
import com.example.ms_categorias.model.Categoria;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
@Component
public class CategoriaModelAssembler implements RepresentationModelAssembler<Categoria, EntityModel<Categoria>> {
@Override
public EntityModel<Categoria> toModel(Categoria categoria) {
return EntityModel.of(categoria,
linkTo(methodOn(CategoriaControllerV2.class).buscarPorId(categoria.getId())).withSelfRel(),
linkTo(methodOn(CategoriaControllerV2.class).obtenerCategorias()).withRel("categorias"));
    }
}
