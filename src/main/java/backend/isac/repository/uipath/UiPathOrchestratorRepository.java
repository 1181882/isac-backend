package backend.isac.repository.uipath;

import backend.isac.model.uipath.UiPathOrchestrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UiPathOrchestratorRepository extends JpaRepository<UiPathOrchestrator, Long> {
}