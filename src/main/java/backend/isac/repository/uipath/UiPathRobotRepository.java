package backend.isac.repository.uipath;

import backend.isac.model.uipath.UiPathRobot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UiPathRobotRepository extends JpaRepository<UiPathRobot, Long> {
}
