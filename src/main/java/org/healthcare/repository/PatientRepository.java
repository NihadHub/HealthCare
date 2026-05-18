package org.healthcare.repository;

import org.healthcare.entity.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page <Patient> findByNomContainingIgnoreCase(String nom, Pageable pageable);
}
