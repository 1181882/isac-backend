package backend.isac.mapper;

import backend.isac.dto.JiraProjectDTO;
import backend.isac.model.JiraProject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JiraProjectMapper {
    JiraProjectDTO toDTO(JiraProject jiraProject);

    JiraProject toEntity(JiraProjectDTO jiraProjectDTO);
}
