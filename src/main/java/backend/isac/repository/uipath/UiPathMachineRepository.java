package backend.isac.repository.uipath;
import backend.isac.model.uipath.UiPathMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UiPathMachineRepository extends JpaRepository<UiPathMachine, Long> {
}
