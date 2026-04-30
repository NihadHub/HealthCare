package org.healthcare.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.healthcare.dto.DossierMedicalDTO;
import org.healthcare.service.DossierMedicalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dossiers-medecaux")
@Tag(name="Dossiers Médecaux", description = "Gestion des dossiers Médecaux")
@RequiredArgsConstructor
public class DossierMedicalController {
    private final DossierMedicalService dossierMedicalService;

@PostMapping
    public ResponseEntity<DossierMedicalDTO> addDossierMedical ( @Valid @RequestBody DossierMedicalDTO dossierMedicalDTO ){
    DossierMedicalDTO added = dossierMedicalService.creerDossier(dossierMedicalDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(added);
}

@PostMapping ("/{id}/diagnostic")
        public ResponseEntity <DossierMedicalDTO> ajouterDiagnostic(@PathVariable Long id, @RequestBody String diagnostic){
    return ResponseEntity.ok(dossierMedicalService.ajouterDiagnostic(id,diagnostic));
}

@PostMapping("/{id}/observation")
    public ResponseEntity<DossierMedicalDTO> ajouterObservation (@PathVariable Long id, @RequestBody String observation){
    return ResponseEntity.ok(dossierMedicalService.ajouterObservation(id,observation));
}

@GetMapping("/{id}")
    public ResponseEntity<DossierMedicalDTO> consulterRdv(@PathVariable Long id){
        return ResponseEntity.ok(dossierMedicalService.consulterDossierMedical(id));
    }
}
