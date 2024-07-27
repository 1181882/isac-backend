package backend.isac.mapper;

import backend.isac.dto.DepartmentDTO;
import backend.isac.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class, ProjectMapper.class})
public interface DepartmentMapper {
    @Mapping(source = "businessLine.id", target = "businessLineId")
    DepartmentDTO toDTO(Department department);

    @Mapping(source = "businessLineId", target = "businessLine.id")
    Department toEntity(DepartmentDTO departmentDTO);
}
