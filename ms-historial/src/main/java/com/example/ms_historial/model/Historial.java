package com.example.ms_historial.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="historial")

public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ElementCollection
    @CollectionTable(
            name = "historial_serie_ids",
            joinColumns = @JoinColumn(name = "historial_id")
    )
    @Column(name = "serie_id")
    private List<Integer> seriesIds;

}
