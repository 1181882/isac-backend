package backend.isac.repository;

import backend.isac.model.AutomatedApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomatedApplicationRepository extends JpaRepository<AutomatedApplication, Long> {
}