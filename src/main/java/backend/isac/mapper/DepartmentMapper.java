package backend.isac.mapper;

import backend.isac.dto.DepartmentDTO;
import backend.isac.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDTO toDTO(Department department);

    Department toEntity(DepartmentDTO departmentDTO);
}
