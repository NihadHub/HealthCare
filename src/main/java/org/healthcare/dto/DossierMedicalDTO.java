package org.healthcare.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class DossierMedicalDTO {
    private Long id ;
    private String diagnostic ;
    private String observation;
    private LocalDateTime dateCreation;

    @NotNull(message = "L'Id du patient est obligatoire ")
    private Long patientId;
    private String patientNom;

}
