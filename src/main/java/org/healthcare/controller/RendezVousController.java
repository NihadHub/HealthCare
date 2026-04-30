package org.healthcare.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.healthcare.dto.RendezvousDTO;
import org.healthcare.service.RendezVousService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/RendezVous")
@Tag(name= "RendezVous", description = "Gestion des Rendez Vous")
@RequiredArgsConstructor
public class RendezVousController {
    private final RendezVousService rendezVousService;

@PostMapping
    public ResponseEntity<RendezvousDTO> addRendezVous(@Valid @RequestBody RendezvousDTO rendezvousDTO){
    RendezvousDTO created = rendezVousService.creerRendezVous(rendezvousDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
}

@PutMapping("/{id}")
    public ResponseEntity<RendezvousDTO> modifierRendezvous (@PathVariable Long id, @Valid @RequestBody RendezvousDTO rendezvousDTO){
    RendezvousDTO modified = rendezVousService.modifierRendezVous(id, rendezvousDTO);
    return ResponseEntity.ok(modified);
}

@PostMapping("/{id}/annuler")
    public ResponseEntity<Void > annulerRendezVous (@PathVariable Long id ){
    rendezVousService.annulerRendezVous(id);
    return ResponseEntity.noContent().build();
}

@GetMapping
    public ResponseEntity<List<RendezvousDTO>> listerRendezVous(){
    List<RendezvousDTO> rendezvousDTOS = rendezVousService.listerRendezVous();
    return ResponseEntity.ok(rendezvousDTOS);
}

@GetMapping ("/patient/{patientId}")
    public ResponseEntity<List<RendezvousDTO>> getRendezVousByPatient (@PathVariable Long patientId){
   List<RendezvousDTO>  rendezvousDTO = rendezVousService.getRendezVousByPatientId(patientId);
   return ResponseEntity.ok(rendezvousDTO);
}

@GetMapping ("/medecin/{medecinId}")
    public ResponseEntity<List<RendezvousDTO>> getRendezVousByMedecin(@PathVariable Long medecinId){
    List<RendezvousDTO> rendezvousDTOS = rendezVousService.getRendezVousByMedecinId(medecinId);
    return ResponseEntity.ok(rendezvousDTOS);
}
}
