package org.healthcare.service;

import lombok.RequiredArgsConstructor;
import org.healthcare.dto.DossierMedicalDTO;
import org.healthcare.dto.PatientDTO;
import org.healthcare.entity.DossierMedical;
import org.healthcare.entity.Patient;
import org.healthcare.mapper.DossierMedicalMapper;
import org.healthcare.repository.DossierMedicalRepository;
import org.healthcare.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DossierMedicalService {
    private final DossierMedicalRepository dossierMedicalRepository;
    private final DossierMedicalMapper dossierMedicalMapper;
    private final PatientRepository patientRepository;

    public DossierMedicalDTO creerDossier(DossierMedicalDTO dossierMedicalDTO){
        patientRepository.findById(dossierMedicalDTO.getPatientId()).orElseThrow(() -> new RuntimeException("Patient non trouve"));
        DossierMedical dossierMedical = dossierMedicalRepository.save(dossierMedicalMapper.toEntity(dossierMedicalDTO));
        DossierMedicalDTO medicalDTO = dossierMedicalMapper.toDTO(dossierMedical);
        medicalDTO.setPatientId(dossierMedical.getPatient().getId());
        return medicalDTO;
    }

    public DossierMedicalDTO ajouterDiagnostic(Long id, String diagnostic){
        DossierMedical dossierMedical = dossierMedicalRepository.findById(id).orElseThrow(() -> new RuntimeException("Dossier non trouvé "));
        dossierMedical.setDiagnostic(diagnostic);
        DossierMedical updateddossier= dossierMedicalRepository.save(dossierMedical);
        return dossierMedicalMapper.toDTO(updateddossier);
    }

    public DossierMedicalDTO ajouterObservation(Long id, String observation){
        DossierMedical dossierMedical = dossierMedicalRepository.findById(id).orElseThrow(() -> new RuntimeException("Dossier non trouvé "));
        dossierMedical.setObservation(observation);
        DossierMedical updatedDossier= dossierMedicalRepository.save(dossierMedical);
        return dossierMedicalMapper.toDTO(updatedDossier);
    }

    public DossierMedicalDTO consulterDossierMedical(Long id){
        DossierMedical dossierMedical = dossierMedicalRepository.findById(id).orElseThrow(() -> new RuntimeException("Dossier non trouvé "));
        return  dossierMedicalMapper.toDTO(dossierMedical);
    }




}
