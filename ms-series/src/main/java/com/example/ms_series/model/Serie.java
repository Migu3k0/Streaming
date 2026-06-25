package com.example.ms_series.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="serie")

public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private Integer temporadas;

    @ElementCollection
    @CollectionTable(
            name = "serie_categoria_ids",
            joinColumns = @JoinColumn(name = "serie_id")
    )
    @Column(name = "categoria_id")
    private List<Integer> categoriasIds;
    
    @ElementCollection
    @CollectionTable(
            name = "serie_episodio_ids",
            joinColumns = @JoinColumn(name = "serie_id")
    )
    @Column(name = "episodio_id")
    private List<Integer> episodiosIds;

    @ElementCollection
    @CollectionTable(
            name = "serie_estudio_ids",
            joinColumns = @JoinColumn(name = "serie_id")
    )
    @Column(name = "estudio_id")
    private List<Integer> estudiosIds;


}
