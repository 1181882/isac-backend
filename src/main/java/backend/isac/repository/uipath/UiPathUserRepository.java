package backend.isac.repository.uipath;

import backend.isac.model.uipath.UiPathUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UiPathUserRepository extends JpaRepository<UiPathUser, Long> {
}
