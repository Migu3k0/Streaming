package com.example.ms_perfil.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="perfil")

public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;

    @ElementCollection
    @CollectionTable(
            name = "perfil_usuario_ids",
            joinColumns = @JoinColumn(name = "perfil_id")
    )
    @Column(name = "usuario_id")
    private List<Integer> usuariosIds;

}
