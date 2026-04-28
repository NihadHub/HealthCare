package org.healthcare.mapper;

import org.healthcare.dto.MedecinDTO;
import org.healthcare.entity.Medecin;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MedecinMapper {
    MedecinDTO toDTO (Medecin medecin);
    Medecin toEntity(MedecinDTO medecinDTO);
    void updateEntityFromDTO(MedecinDTO dto, @MappingTarget Medecin entity);

}
