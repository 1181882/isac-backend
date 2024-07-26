package backend.isac.mapper;

import backend.isac.dto.BusinessLineDTO;
import backend.isac.model.BusinessLine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusinessLineMapper {
    BusinessLineDTO toDTO(BusinessLine businessLine);

    BusinessLine toEntity(BusinessLineDTO businessLineDTO);
}
