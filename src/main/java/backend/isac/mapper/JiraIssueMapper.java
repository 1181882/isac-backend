package backend.isac.mapper;

import backend.isac.dto.JiraIssueDTO;
import backend.isac.model.JiraIssue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JiraIssueMapper {
    @Mapping(target = "processId", source = "process.id")
    JiraIssueDTO toDTO(JiraIssue jiraIssue);

    @Mapping(target = "process.id", source = "processId")
    JiraIssue toEntity(JiraIssueDTO jiraIssueDTO);
}
