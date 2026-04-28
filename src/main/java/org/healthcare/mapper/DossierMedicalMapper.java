package org.healthcare.mapper;

import org.healthcare.dto.DossierMedicalDTO;
import org.healthcare.entity.DossierMedical;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DossierMedicalMapper {
    @Mapping(source = "patient.id", target = "idPatient")
    @Mapping(source = "patient.nom", target = "nomPatient")
    DossierMedicalDTO toDTO(DossierMedical dossierMedical);

    @Mapping(target = "patient", ignore=true)
    DossierMedical toEntity(DossierMedicalDTO dossierMedicalDTO);
    @Mapping(target = "patient", ignore = true)
    void updateEntityFromDTO(DossierMedicalDTO dto, @MappingTarget DossierMedical entity);


}
