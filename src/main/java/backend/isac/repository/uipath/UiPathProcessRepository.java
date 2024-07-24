package backend.isac.repository.uipath;

import backend.isac.model.uipath.UiPathProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UiPathProcessRepository extends JpaRepository<UiPathProcess, Long> {
}
