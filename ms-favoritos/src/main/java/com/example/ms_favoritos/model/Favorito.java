package com.example.ms_favoritos.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="favorito")

public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ElementCollection
    @CollectionTable(
            name = "favorito_serie_ids",
            joinColumns = @JoinColumn(name = "favorito_id")
    )
    @Column(name = "serie_id")
    private List<Integer> seriesIds;

}
