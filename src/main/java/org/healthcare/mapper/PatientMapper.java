package org.healthcare.mapper;
import org.healthcare.dto.PatientDTO;
import org.healthcare.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDTO toDTO(Patient patient);
    Patient toEntity(PatientDTO patientDTO);
    void updateEntityFormDTO(PatientDTO dto, @MappingTarget Patient entity);

}
