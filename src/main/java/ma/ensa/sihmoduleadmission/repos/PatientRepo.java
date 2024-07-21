package ma.ensa.sihmoduleadmission.repos;

import ma.ensa.sihmoduleadmission.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PatientRepo extends JpaRepository<Patient,String> {
    @Modifying
    @Transactional
    @Query("UPDATE Patient p SET p.password = :newPassword WHERE p.CNE = :CNI")
    void updateByCNEPassword(String CNI , String newPassword);
}

