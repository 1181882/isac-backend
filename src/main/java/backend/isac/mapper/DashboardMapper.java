package backend.isac.mapper;

import backend.isac.dto.DashboardDTO;
import backend.isac.model.Dashboard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DashboardMapper {
    DashboardDTO toDTO(Dashboard dashboard);

    Dashboard toEntity(DashboardDTO dashboardDTO);
}
