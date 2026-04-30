package org.healthcare.mapper;

import org.healthcare.dto.DossierMedicalDTO;
import org.healthcare.entity.DossierMedical;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DossierMedicalMapper {
    DossierMedicalDTO toDTO(DossierMedical dossierMedical);

    @Mapping(target = "patient", ignore=true)
    @Mapping(source = "patientId" , target = "patient.id")
    DossierMedical toEntity(DossierMedicalDTO dossierMedicalDTO);
    @Mapping(target = "patient", ignore = true)
    @Mapping(source = "patientId" , target = "patient.id")
    void updateEntityFromDTO(DossierMedicalDTO dto, @MappingTarget DossierMedical entity);

}
