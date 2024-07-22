package backend.isac.repository;

import backend.isac.model.ProjectVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectVersionRepository extends JpaRepository<ProjectVersion, Long> {
}
