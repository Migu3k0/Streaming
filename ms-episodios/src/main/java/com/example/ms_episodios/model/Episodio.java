package com.example.ms_episodios.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="episodio")

public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private double duracion;
    private String fechaSalida;

    @ElementCollection
    @CollectionTable(
            name = "episodio_serie_ids",
            joinColumns = @JoinColumn(name = "episodio_id")
    )
    @Column(name = "serie_id")
    private List<Integer> seriesIds;

}
