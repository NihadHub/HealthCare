package org.healthcare.service;

import lombok.RequiredArgsConstructor;
import org.healthcare.dto.MedecinDTO;
import org.healthcare.entity.Medecin;
import org.healthcare.mapper.MedecinMapper;
import org.healthcare.repository.MedecinRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MedecinService {
    private final MedecinRepository medecinRepository;
    private final MedecinMapper medecinMapper;

    public MedecinDTO ajouterMedecin(MedecinDTO medecinDTO){
        Medecin medecin= medecinMapper.toEntity(medecinDTO);
        Medecin saveMedecin = medecinRepository.save(medecin);
        return medecinMapper.toDTO(saveMedecin);
    }

    public MedecinDTO modifierMedecin(Long id, MedecinDTO medecinDTO){
        Medecin medecin = medecinRepository.findById(id).orElseThrow(() -> new RuntimeException("Medecin non trouvé "));
        medecinMapper.updateEntityFromDTO(medecinDTO, medecin);
        Medecin medecinModofier = medecinRepository.save(medecin);
        return medecinMapper.toDTO(medecinModofier);
    }

    public void supprimerMedecin (Long id){
        medecinRepository.deleteById(id);
    }

    public List<MedecinDTO> getAll(){
        List<Medecin> medecins = medecinRepository.findAll();
        return medecins.stream().map(medecinMapper:: toDTO).toList();
    }
}
