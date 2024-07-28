package backend.isac.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CompanyDTO extends BaseEntityDTO {
    private List<BusinessLineDTO> businessLines;
}