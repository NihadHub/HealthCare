package org.healthcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.healthcare.enums.StatutRendezVous;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  LocalDateTime dateRendezVous;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutRendezVous statutRendezVous;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "patient_id")
    private  Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="medecin_id")
    private Medecin medecin;

}
