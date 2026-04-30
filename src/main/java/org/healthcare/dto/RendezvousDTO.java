package org.healthcare.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.healthcare.enums.StatutRendezVous;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RendezvousDTO {
    private  Long id;

    @NotNull(message = "La date est obligatoire")
    private LocalDateTime dateRendezVous;

    @NotNull(message="le statut est obligatoire")
    private StatutRendezVous statutRendezVous;

    @NotNull(message = "L'ID du patient est obligatoire")
    private Long patientId;

    @NotNull(message = "L'ID du medecin est obligatoire")
    private  Long medecinId;

    private String patientNom;
    private String medecinNom;
}
