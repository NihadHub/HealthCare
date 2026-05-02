package org.healthcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class DossierMedical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String diagnostic;
    private String observation;

    @Column(nullable = false)
    private LocalDateTime dateCreation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "patient_id")
    private Patient patient;
}
