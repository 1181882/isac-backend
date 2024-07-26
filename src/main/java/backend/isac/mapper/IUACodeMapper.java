package backend.isac.mapper;

import backend.isac.dto.IUACodeDTO;
import backend.isac.model.IUACode;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUACodeMapper {
    IUACodeDTO toDTO(IUACode iuaCode);

    IUACode toEntity(IUACodeDTO iuaCodeDTO);
}
