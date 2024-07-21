package backend.isac.repository;
import backend.isac.model.ProfessionalFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalFunctionRepository extends JpaRepository<ProfessionalFunction, Long> {
}
