package backend.isac.repository;

import backend.isac.model.IUACode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUACodeRepository extends JpaRepository<IUACode, Long> {
}
