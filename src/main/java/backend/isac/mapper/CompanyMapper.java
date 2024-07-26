package backend.isac.mapper;

import backend.isac.dto.CompanyDTO;
import backend.isac.model.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDTO toDTO(Company company);

    Company toEntity(CompanyDTO companyDTO);
}
