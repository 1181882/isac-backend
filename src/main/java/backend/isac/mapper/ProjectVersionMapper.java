package backend.isac.mapper;

import backend.isac.dto.ProjectVersionDTO;
import backend.isac.model.ProjectVersion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectVersionMapper {
    ProjectVersionDTO toDTO(ProjectVersion projectVersion);

    ProjectVersion toEntity(ProjectVersionDTO projectVersionDTO);
}
