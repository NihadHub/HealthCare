package org.healthcare.service;

import org.healthcare.dto.MedecinDTO;
import org.healthcare.dto.PatientDTO;
import org.healthcare.dto.RendezvousDTO;
import org.healthcare.entity.Medecin;
import org.healthcare.entity.Patient;
import org.healthcare.enums.StatutRendezVous;
import org.healthcare.mapper.MedecinMapper;
import org.healthcare.mapper.PatientMapper;
import org.healthcare.mapper.RendezVousMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class RendezVousServiceTest {

    @Autowired
    private RendezVousService rendezVousService;
    @Autowired
    private RendezVousMapper rendezVousMapper;
    @Autowired
    private MedecinService medecinService;
    @Autowired
    private MedecinMapper medecinMapper;
    @Autowired
    private PatientService patientService;
    @Autowired
    private  PatientMapper patientMapper;

    private RendezvousDTO sampleRendezVous(){
        Patient p = new Patient();
        p.setNom("Test");
        p.setPrenom("Patient");
        p.setEmail("p@gmail.com");
        p.setTelephone("0600000");
        p.setDateNaissance(LocalDate.of(1990, 1, 1));
        PatientDTO patientDTO = patientService.ajouterPatient(patientMapper.toDTO(p));

        Medecin m = new Medecin();
        m.setNom("Dr. Test");
        m.setSpecialite("Generaliste");
        m.setEmail("m@gmail.com");
        m.setTelephone("0000000");
        MedecinDTO medecinDTO = medecinService.ajouterMedecin(medecinMapper.toDTO(m));

        RendezvousDTO rv = new RendezvousDTO();
        rv.setPatientId(patientDTO.getId());
        rv.setMedecinId(medecinDTO.getId());
        rv.setStatutRendezVous(StatutRendezVous.PROGRAMME);
        rv.setDateRendezVous(LocalDateTime.of(2026, 10 ,20, 12, 20));
        return rv;
    }

    @Test
    void creerRendezVous() {
        Patient patient= new Patient();
        patient.setNom("Ouachour");
        patient.setPrenom("Nihad");
        patient.setEmail("bono@gmail.com");
        patient.setTelephone("0600000");
        patient.setDateNaissance(LocalDate.parse("1944-01-01"));
        patient.setRendezVous(List.of());
        PatientDTO patientDTO = patientService.ajouterPatient(patientMapper.toDTO(patient));

        Medecin medecin = new Medecin();
        medecin.setNom("Ahmed");
        medecin.setSpecialite("cardiologue");
        medecin.setEmail("bonooo@gmail.com");
        medecin.setTelephone("0000000");
        medecin.setRendezVous(List.of());
        MedecinDTO medecinDTO = medecinService.ajouterMedecin(medecinMapper.toDTO(medecin));

        RendezvousDTO rendezVous = new RendezvousDTO();
        rendezVous.setStatutRendezVous(StatutRendezVous.PROGRAMME);
        rendezVous.setPatientId(patientDTO.getId());
        rendezVous.setMedecinId(medecinDTO.getId());
        rendezVous.setDateRendezVous(LocalDateTime.of(2026, 9,1 ,10 , 30));

        RendezvousDTO rendezvousDTO = rendezVousService.creerRendezVous(rendezVous);
        assertEquals(rendezVous.getStatutRendezVous() , rendezvousDTO.getStatutRendezVous());

    }

    @Test
    void annulerRendezVous() {
        RendezvousDTO rendezvousDTO = sampleRendezVous();
        RendezvousDTO created = rendezVousService.creerRendezVous(rendezvousDTO);

        RendezvousDTO calncled = rendezVousService.annulerRendezVous(created.getId());

        assertNotNull(calncled);
        assertEquals(StatutRendezVous.ANNULER, calncled.getStatutRendezVous());
    }

    @Test
    void modifierRendezVous() {
        RendezvousDTO created = rendezVousService.creerRendezVous(sampleRendezVous());
        created.setDateRendezVous(LocalDateTime.of(2026, 12, 25, 14, 0));
        RendezvousDTO modified = rendezVousService.modifierRendezVous(created.getId(), created);
        assertEquals(LocalDateTime.of(2026, 12, 25, 14, 0), modified.getDateRendezVous());
    }

    @Test
    void getRendezVousByPatientId() {
        RendezvousDTO rendezvousDTO = sampleRendezVous();
        rendezVousService.creerRendezVous(rendezvousDTO);
        List<RendezvousDTO> list = rendezVousService.getRendezVousByPatientId(rendezvousDTO.getPatientId());
        assertFalse(list.isEmpty());
        assertEquals(rendezvousDTO.getPatientId(), list.get(0).getPatientId());
    }

    @Test
    void getRendezVousByMedecinId() {
        RendezvousDTO rendezvousDTO = sampleRendezVous();
        rendezVousService.creerRendezVous(rendezvousDTO);
        List<RendezvousDTO> list = rendezVousService.getRendezVousByMedecinId(rendezvousDTO.getMedecinId());
        assertFalse(list.isEmpty());
        assertEquals(rendezvousDTO.getMedecinId(), list.get(0).getMedecinId());
    }
}