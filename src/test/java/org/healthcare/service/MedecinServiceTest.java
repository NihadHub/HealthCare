package org.healthcare.service;

import org.healthcare.dto.MedecinDTO;
import org.healthcare.entity.Medecin;
import org.healthcare.mapper.MedecinMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class MedecinServiceTest {
@Autowired
    private MedecinService medecinService;
@Autowired
    private MedecinMapper medecinMapper;

    @Test
    void supprimerMedecin() {
        Medecin medecin = new Medecin();
        medecin.setNom("Ahmed");
        medecin.setSpecialite("cardiologue");
        medecin.setEmail("ahmed@gmail.com");
        medecin.setTelephone("0000000");
        medecin.setRendezVous(List.of());
        MedecinDTO medecinDTO = medecinService.ajouterMedecin(medecinMapper.toDTO(medecin));
        assertNotNull(medecinDTO);
        medecinService.supprimerMedecin(medecinDTO.getId());
        MedecinDTO deletedMedecin = medecinService.findById(medecinDTO.getId());
        assertNull(deletedMedecin);
    }

}