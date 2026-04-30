package org.healthcare.service;

import org.healthcare.dto.PatientDTO;
import org.healthcare.entity.Patient;
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
class PatientServiceTest {
@Autowired
private PatientMapper patientMapper;
@Autowired
private PatientService patientService;
    @Test
    void ajouterPatient() {
        Patient patient= new Patient();
        patient.setNom("Ouachour");
        patient.setPrenom("Nihad");
        patient.setEmail("ouachour@gmail.com");
        patient.setTelephone("0600000");
        patient.setDateNaissance(LocalDate.parse("1944-01-01"));
        patient.setRendezVous(List.of());

        PatientDTO patientDTO= patientService.ajouterPatient(patientMapper.toDTO(patient));
        assertNotNull(patientDTO);
        assertEquals(patientMapper.toEntity(patientDTO).getClass(), patient.getClass());
        assertEquals(patientDTO.getNom(), patient.getNom());
    }
}