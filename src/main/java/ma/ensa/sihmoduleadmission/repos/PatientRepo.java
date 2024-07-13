package ma.ensa.sihmoduleadmission.repos;

import ma.ensa.sihmoduleadmission.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient,String> {
}
