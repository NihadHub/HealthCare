package org.healthcare.repository;

import org.healthcare.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RendezVousRepository extends JpaRepository <RendezVous,Long> {
    @Query("select r from RendezVous r where r.patient.id = :id")
    List<RendezVous> findByPatientId(@Param("id") Long id);
    @Query("select r from RendezVous r where r.medecin.id = :id")
    List<RendezVous> findByMedecinId(@Param("id") Long id);
}
