package backend.isac.mapper;

import backend.isac.dto.ProcessDTO;
import backend.isac.model.Process;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProcessMapper {
    ProcessDTO toDTO(Process process);

    Process toEntity(ProcessDTO processDTO);
}
