package backend.isac.repository.uipath;

import backend.isac.model.uipath.UiPathLicense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UiPathLicenseRepository extends JpaRepository<UiPathLicense, Long> {
}
