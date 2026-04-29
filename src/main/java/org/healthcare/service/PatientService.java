package org.healthcare.service;

import lombok.RequiredArgsConstructor;
import org.healthcare.dto.PatientDTO;
import org.healthcare.entity.Patient;
import org.healthcare.mapper.PatientMapper;
import org.healthcare.repository.PatientRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientDTO ajouterPatient(PatientDTO patientDTO){
        Patient patient = patientMapper.toEntity(patientDTO);
        Patient saved= patientRepository.save(patient);
        return patientMapper.toDTO(saved);
    }

    public PatientDTO modifierPatient(Long id ,PatientDTO patientDTO){
        Patient patient= patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient non trouvé "));
        patientMapper.updateEntityFormDTO(patientDTO,patient);
        Patient patientmodifie= patientRepository.save(patient);
        return patientMapper.toDTO((patientmodifie));
    }

    public void supprimerPatient(Long id){
         patientRepository.deleteById(id);
    }

    @Transactional
    public PatientDTO getById(Long id){
        Patient patient= patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient non trouvé "));
        return patientMapper.toDTO(patient);
    }

    @Transactional
    public List<PatientDTO> getAll(){
        List<Patient> patients= patientRepository.findAll();
        return patients.stream().map(patientMapper::toDTO).toList();
    }

}
