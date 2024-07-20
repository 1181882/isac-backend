package backend.isac.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompanyDTO extends BaseEntityDTO {
    private List<BusinessLineDTO> businessLines;
}