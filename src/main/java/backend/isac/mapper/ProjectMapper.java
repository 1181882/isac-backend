package backend.isac.mapper;

import backend.isac.dto.ProjectDTO;
import backend.isac.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "iuaCode.id", target = "iuaCodeId")
    ProjectDTO toDTO(Project project);

    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "iuaCodeId", target = "iuaCode.id")
    @Mapping(target = "projectVersions", ignore = true)
    Project toEntity(ProjectDTO projectDTO);
}
