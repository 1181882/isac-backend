package backend.isac.mapper;

import backend.isac.dto.EmployeeDTO;
import backend.isac.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target = "projectVersionIds", ignore = true)
    EmployeeDTO toDTO(Employee employee);

    @Mapping(target = "projectVersions", ignore = true)
    Employee toEntity(EmployeeDTO employeeDTO);
}
