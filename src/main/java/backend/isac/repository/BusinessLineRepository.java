package backend.isac.repository;

import backend.isac.model.BusinessLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessLineRepository extends JpaRepository<BusinessLine, Long> {

}
