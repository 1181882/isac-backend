package backend.isac.repository.uipath;

import backend.isac.model.uipath.UiPathEnvironment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UiPathEnvironmentRepository extends JpaRepository<UiPathEnvironment, Long> {
}
