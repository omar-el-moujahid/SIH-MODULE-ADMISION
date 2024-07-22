package ma.ensa.sihmoduleadmission.repos;

import ma.ensa.sihmoduleadmission.entities.Medical_History;
import ma.ensa.sihmoduleadmission.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Medical_HistoryRepo extends JpaRepository<Medical_History,Long> {
    List<Medical_History> findByPatient(Patient patient);
}
