package org.healthcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import java.util.List;
import java.util.ArrayList;


@Getter
@Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String specialite;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telephone;

    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL)
    private List<RendezVous> rendezVous = new ArrayList<>();
}
