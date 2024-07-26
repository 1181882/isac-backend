package backend.isac.mapper;

import backend.isac.dto.JiraIssueDTO;
import backend.isac.model.JiraIssue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JiraIssueMapper {
    JiraIssueDTO toDTO(JiraIssue jiraIssue);

    JiraIssue toEntity(JiraIssueDTO jiraIssueDTO);
}
