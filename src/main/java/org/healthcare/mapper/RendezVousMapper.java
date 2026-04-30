package org.healthcare.mapper;

import org.healthcare.dto.RendezvousDTO;
import org.healthcare.entity.RendezVous;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RendezVousMapper {
    RendezvousDTO toDTO(RendezVous rendezVous);

   @Mapping(target="patient", ignore=true)
    @Mapping(target = "medecin", ignore = true)
    RendezVous toEntity(RendezvousDTO dto);

   @Mapping(target = "patient", ignore = true)
    @Mapping(target = "medecin", ignore = true)
    void updateEntityFromDTO(RendezvousDTO dto, @MappingTarget RendezVous entity);

}

