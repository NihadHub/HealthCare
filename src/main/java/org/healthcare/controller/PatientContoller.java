package org.healthcare.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.healthcare.dto.PatientDTO;
import org.healthcare.entity.Patient;
import org.healthcare.mapper.PatientMapper;
import org.healthcare.repository.PatientRepository;
import org.healthcare.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@Tag(name="Patients", description = "API de gestion des pestions")
@RequiredArgsConstructor
public class PatientContoller {
    private final PatientService patientService;

 @PostMapping
 public ResponseEntity<PatientDTO> addPatient( @Valid @RequestBody PatientDTO patientDTO){
     PatientDTO added= patientService.ajouterPatient(patientDTO);
     return ResponseEntity.status(HttpStatus.CREATED).body(added);
 }

@PutMapping ("/{id}")
    public ResponseEntity<PatientDTO> modifyPatient(@PathVariable Long id, @Valid @RequestBody PatientDTO patientDTO){
     PatientDTO mofidified= patientService.modifierPatient(id, patientDTO);
     return ResponseEntity.ok(mofidified);
}

@DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id){
     patientService.supprimerPatient(id);
     return ResponseEntity.noContent().build();
}

@GetMapping
    public ResponseEntity<Page<Patient>> ListerPatients(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam (defaultValue = "10") int size,
                                                        @RequestParam (defaultValue = "nom") String sortBy){

     return ResponseEntity.ok(patientService.getAll(page,size,sortBy));
}

@GetMapping("/search")
public ResponseEntity <Page<Patient>> searchPatient(
        @RequestParam String nom,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size){
     return ResponseEntity.ok(patientService.getPatientByName(page,size,nom));
}
@GetMapping ("/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable Long id){
     PatientDTO patientDTO=patientService.getById(id);
     return ResponseEntity.ok(patientDTO);

    }
}