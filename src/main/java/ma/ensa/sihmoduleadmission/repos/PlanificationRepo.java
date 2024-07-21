package ma.ensa.sihmoduleadmission.repos;

import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.entities.Planification;
import ma.ensa.sihmoduleadmission.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface PlanificationRepo extends JpaRepository<Planification,Long> {
    @Transactional
    @Query("SELECT p FROM Planification p JOIN p.specialties s WHERE s.id = :specialtyId AND p.date > :date AND p.currentcapacity < p.capacity ORDER BY p.date ASC")
    Planification findAvailablePlanifications(Long specialtyId,Date date);
}
