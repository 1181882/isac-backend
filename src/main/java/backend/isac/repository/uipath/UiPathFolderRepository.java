package backend.isac.repository.uipath;

import backend.isac.model.uipath.UiPathFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UiPathFolderRepository extends JpaRepository<UiPathFolder, Long> {
}
