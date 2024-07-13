package ma.ensa.sihmoduleadmission.repos;

import ma.ensa.sihmoduleadmission.entities.Medical_History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Medical_HistoryRepo extends JpaRepository<Medical_History,Long> {
}
