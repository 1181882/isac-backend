package backend.isac.dto;

import backend.isac.model.enums.EEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessLineDTO extends BaseEntityDTO {
    private List<DepartmentDTO> departments;
    private EEntity entity;
    private Long companyId;
}
