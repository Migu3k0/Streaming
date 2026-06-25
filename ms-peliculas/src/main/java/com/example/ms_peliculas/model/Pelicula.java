package com.example.ms_peliculas.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="pelicula")

public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private Float duracion;

    @ElementCollection
    @CollectionTable(
            name = "pelicula_categoria_ids",
            joinColumns = @JoinColumn(name = "pelicula_id")
    )
    @Column(name = "categoria_id")
    private List<Integer> categoriasIds;
    
    @ElementCollection
    @CollectionTable(
            name = "pelicula_estudio_ids",
            joinColumns = @JoinColumn(name = "pelicula_id")
    )
    @Column(name = "estudio_id")
    private List<Integer> estudiosIds;


}
