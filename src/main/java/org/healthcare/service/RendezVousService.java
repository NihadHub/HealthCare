package org.healthcare.service;

import lombok.RequiredArgsConstructor;
import org.healthcare.dto.RendezvousDTO;
import org.healthcare.entity.Medecin;
import org.healthcare.entity.Patient;
import org.healthcare.entity.RendezVous;
import org.healthcare.enums.StatutRendezVous;
import org.healthcare.mapper.RendezVousMapper;
import org.healthcare.repository.MedecinRepository;
import org.healthcare.repository.PatientRepository;
import org.healthcare.repository.RendezVousRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RendezVousService {
    private final RendezVousRepository rendezVousRepository;
    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;
    private final RendezVousMapper rendezVousMapper;

    public RendezvousDTO creerRendezVous(RendezvousDTO rendezvousDTO){
        Patient patient = patientRepository.findById(rendezvousDTO.getPatientId()).orElseThrow(() -> new RuntimeException("Patient non trouvé "));
        Medecin medecin= medecinRepository.findById(rendezvousDTO.getMedecinId()).orElseThrow(() -> new RuntimeException("Medecin non trouvé "));
        RendezVous rendezVous= rendezVousMapper.toEntity(rendezvousDTO);
        rendezVous.setPatient(patient);
        rendezVous.setMedecin(medecin);

        RendezVous rendezVous1 = rendezVousRepository.save(rendezVous);
        return  rendezVousMapper.toDTO(rendezVous1);
    }

    public RendezvousDTO annulerRendezVous(Long id){
        RendezVous rendezVous= rendezVousRepository.findById(id).orElseThrow(() -> new RuntimeException("Rendez vous non trouvé "));
        rendezVous.setStatutRendezVous(StatutRendezVous.ANNULER);
        return rendezVousMapper.toDTO(rendezVousRepository.save(rendezVous));
    }

    public RendezvousDTO modifierRendezVous(Long id, RendezvousDTO rendezvousDTO){
        RendezVous rendezVous= rendezVousRepository.findById(id).orElseThrow(() -> new RuntimeException("Rendez vous non trouvé "));
        rendezVousMapper.updateEntityFromDTO(rendezvousDTO,rendezVous);
        RendezVous rendezVousModifie= rendezVousRepository.save(rendezVous);
        return rendezVousMapper.toDTO(rendezVousModifie);
    }

    @Transactional
    public List<RendezvousDTO> listerRendezVous(){
        return rendezVousRepository.findAll().stream().map(rendezVousMapper:: toDTO).toList();
    }

    @Transactional
    public List<RendezvousDTO> getRendezVousByPatientId(Long id){
        return rendezVousRepository.findByPatientId(id).stream().map(rendezVousMapper::toDTO).toList();

    }

    @Transactional
    public List<RendezvousDTO> getRendezVousByMedecinId(Long id){
        return rendezVousRepository.findByMedecinId(id).stream().map(rendezVousMapper::toDTO).toList();
    }
}
