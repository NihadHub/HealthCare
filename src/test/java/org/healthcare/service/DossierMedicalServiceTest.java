package org.healthcare.service;

import org.healthcare.dto.DossierMedicalDTO;
import org.healthcare.dto.MedecinDTO;
import org.healthcare.dto.PatientDTO;
import org.healthcare.entity.DossierMedical;
import org.healthcare.entity.Medecin;
import org.healthcare.entity.Patient;
import org.healthcare.mapper.DossierMedicalMapper;
import org.healthcare.mapper.MedecinMapper;
import org.healthcare.mapper.PatientMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class DossierMedicalServiceTest {
@Autowired
private DossierMedicalService dossierMedicalService;
@Autowired
private MedecinService medecinService;
@Autowired
private MedecinMapper medecinMapper;
@Autowired
private PatientService patientService;
@Autowired
private PatientMapper patientMapper;
    @Autowired
    private DossierMedicalMapper dossierMedicalMapper;

    @Test
    void ajouterDiagnostic() {
        Patient patient= new Patient();
        patient.setNom("Ouachour");
        patient.setPrenom("Nihad");
        patient.setEmail("sssssdssqbdss@gmail.com");
        patient.setTelephone("0600000");
        patient.setDateNaissance(LocalDate.parse("1944-01-01"));
        patient.setRendezVous(List.of());

        Medecin medecin = new Medecin();
        medecin.setNom("Ahmed");
        medecin.setSpecialite("cardiologue");
        medecin.setEmail("ahmsqsdsqsfd@gmail.com");
        medecin.setTelephone("0000000");
        medecin.setRendezVous(List.of());

        PatientDTO patientDTO= patientService.ajouterPatient(patientMapper.toDTO(patient));
        MedecinDTO medecinDTO = medecinService.ajouterMedecin(medecinMapper.toDTO(medecin));

        DossierMedicalDTO dossierMedical = new DossierMedicalDTO();
        dossierMedical.setPatientId(patientDTO.getId());
        dossierMedical.setDateCreation(LocalDateTime.now());

        DossierMedicalDTO dossierMedicalDTO=dossierMedicalService.creerDossier(dossierMedical);
        DossierMedicalDTO saved = dossierMedicalService.ajouterDiagnostic(dossierMedicalDTO.getId(),"test diagnostic");

        assertNotNull(saved);
        assertEquals("test diagnostic", saved.getDiagnostic() );


    }


}