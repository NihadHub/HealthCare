package org.healthcare.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.healthcare.dto.MedecinDTO;
import org.healthcare.service.MedecinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medecin")
@Tag(name="Medecins", description = "API de gestion des Medecins")
@RequiredArgsConstructor
public class MedecinController {
    private final MedecinService medecinService;

@PostMapping
    public ResponseEntity<MedecinDTO> addMedecin(@Valid @RequestBody MedecinDTO medecinDTO){
    MedecinDTO created= medecinService.ajouterMedecin(medecinDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(medecinDTO);
}

@PutMapping ("/{id}")
    public ResponseEntity<MedecinDTO> modifyMedecin(@PathVariable Long id, @Valid @RequestBody MedecinDTO medecinDTO){
    MedecinDTO modified = medecinService.modifierMedecin(id, medecinDTO);
    return ResponseEntity.ok(modified);
}

@DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteMedecin (@PathVariable Long id){
    medecinService.supprimerMedecin(id);
    return ResponseEntity.noContent().build();
}

@GetMapping
    public ResponseEntity<List<MedecinDTO>> ListerMedecin(){
    List<MedecinDTO> medecinDTOS = medecinService.getAll();
    return ResponseEntity.ok(medecinDTOS);
}


}
