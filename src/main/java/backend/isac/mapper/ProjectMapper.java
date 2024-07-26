package backend.isac.mapper;

import backend.isac.dto.ProjectDTO;
import backend.isac.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    @Mapping(source = "iuaCode.id", target = "iuaCodeId")
    ProjectDTO toDTO(Project project);

    @Mapping(source = "iuaCodeId", target = "iuaCode.id")
    Project toEntity(ProjectDTO projectDTO);
}
