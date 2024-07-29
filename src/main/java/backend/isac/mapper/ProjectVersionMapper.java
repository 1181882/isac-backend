package backend.isac.mapper;

import backend.isac.dto.ProjectVersionDTO;
import backend.isac.model.ProjectVersion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectVersionMapper {
    ProjectVersionDTO toDTO(ProjectVersion projectVersion);

    @Mapping(target = "jiraIssues", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "uipathProcesses", ignore = true)
    @Mapping(target = "documents", ignore = true)
    ProjectVersion toEntity(ProjectVersionDTO projectVersionDTO);
}