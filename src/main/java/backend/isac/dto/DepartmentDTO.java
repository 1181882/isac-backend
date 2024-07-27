package backend.isac.dto;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentDTO extends BaseEntityDTO {

    private Long businessLineId;
    private List<EmployeeDTO> employees;
    private List<ProjectDTO> projects;
}
