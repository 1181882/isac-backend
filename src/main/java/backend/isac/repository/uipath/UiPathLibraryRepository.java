package backend.isac.repository.uipath;

import backend.isac.model.uipath.UiPathLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UiPathLibraryRepository extends JpaRepository<UiPathLibrary, Long> {
}
