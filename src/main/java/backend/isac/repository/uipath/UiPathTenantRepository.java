package backend.isac.repository.uipath;

import backend.isac.model.uipath.UiPathTenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UiPathTenantRepository extends JpaRepository<UiPathTenant, Long> {
}
