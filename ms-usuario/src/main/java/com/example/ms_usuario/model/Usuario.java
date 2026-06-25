package com.example.ms_usuario.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="usuario")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String email;
    private String contrasenia;

    @ElementCollection
    @CollectionTable(
            name = "usuario_perfil_ids",
            joinColumns = @JoinColumn(name = "usuario_id")
    )
    @Column(name = "perfil_id")
    private List<Integer> perfilesIds;

}
