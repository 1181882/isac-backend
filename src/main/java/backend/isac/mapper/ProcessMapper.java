package backend.isac.mapper;

import backend.isac.dto.ProcessDTO;
import backend.isac.model.Process;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProcessMapper {
    @Mapping(target = "jiraIssues", source = "jiraIssues")
    ProcessDTO toDTO(Process process);

    @Mapping(target = "jiraIssues", source = "jiraIssues")
    Process toEntity(ProcessDTO processDTO);
}
