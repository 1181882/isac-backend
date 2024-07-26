package backend.isac.mapper;

import backend.isac.dto.ProfessionalFunctionDTO;
import backend.isac.model.ProfessionalFunction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfessionalFunctionMapper {
    ProfessionalFunctionDTO toDTO(ProfessionalFunction professionalFunction);

    ProfessionalFunction toEntity(ProfessionalFunctionDTO professionalFunctionDTO);
}
