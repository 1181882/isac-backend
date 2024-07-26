package backend.isac.mapper;

import backend.isac.dto.AutomatedApplicationDTO;
import backend.isac.model.AutomatedApplication;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutomatedApplicationMapper {
    AutomatedApplicationDTO toDTO(AutomatedApplication automatedApplication);

    AutomatedApplication toEntity(AutomatedApplicationDTO automatedApplicationDTO);
}
