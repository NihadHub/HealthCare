package org.healthcare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PatientDTO {
    private  Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private  String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private  String prenom;

    @NotBlank(message = "L'email est obligatoire")
    @Email
    private String email;

    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;

    @NotBlank(message = "La date de naissance est obligatoire")
    private LocalDate dateNaissance;
}
