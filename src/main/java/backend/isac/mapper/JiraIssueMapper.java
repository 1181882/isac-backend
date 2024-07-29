package backend.isac.mapper;

import backend.isac.dto.JiraIssueDTO;
import backend.isac.model.JiraIssue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JiraIssueMapper {
    @Mapping(target = "projectVersionIds", ignore = true)
    JiraIssueDTO toDTO(JiraIssue jiraIssue);

    @Mapping(target = "projectVersions", ignore = true)
    JiraIssue toEntity(JiraIssueDTO jiraIssueDTO);
}
