package ma.ensa.sihmoduleadmission.repos;

import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.entities.Planification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanificationRepo extends JpaRepository<Planification,Long> {
}
