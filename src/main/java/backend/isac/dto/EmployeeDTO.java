package backend.isac.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private boolean isActive;
    private boolean isInternal;
    private Long departmentId;
    private Long professionalFunctionId;
}
