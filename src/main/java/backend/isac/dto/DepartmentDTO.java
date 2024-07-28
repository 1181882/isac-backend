package backend.isac.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DepartmentDTO extends BaseEntityDTO {

    private Long businessLineId;
    private List<EmployeeDTO> employees;
    private List<ProjectDTO> projects;
}
