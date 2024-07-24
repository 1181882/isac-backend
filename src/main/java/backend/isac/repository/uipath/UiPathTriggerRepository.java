package backend.isac.repository.uipath;

import backend.isac.model.uipath.UiPathTrigger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UiPathTriggerRepository extends JpaRepository<UiPathTrigger, Long> {
}
