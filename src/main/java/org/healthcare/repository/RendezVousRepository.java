package org.healthcare.repository;

import org.healthcare.entity.Medecin;
import org.healthcare.entity.Patient;
import org.healthcare.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RendezVousRepository extends JpaRepository <RendezVous,Long> {
    List<RendezVous> findByPatient(Long patientId);
    List<RendezVous> findByMedecin(Long medecinId);
}
