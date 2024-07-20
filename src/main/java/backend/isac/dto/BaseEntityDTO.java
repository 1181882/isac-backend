package backend.isac.dto;

import lombok.Data;

@Data
public abstract class BaseEntityDTO {
    private Long id;
    private String abbreviation;
    private String fullName;
}
