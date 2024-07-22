package backend.isac.repository;

import backend.isac.model.JiraProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JiraProjectRepository extends JpaRepository<JiraProject, Long> {
}
