package ma.ensa.sihmoduleadmission.repos;

import ma.ensa.sihmoduleadmission.entities.Doctor;
import ma.ensa.sihmoduleadmission.entities.Planification;
import ma.ensa.sihmoduleadmission.entities.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface PlanificationRepo extends JpaRepository<Planification,Long> {
    @Transactional
    @Query("SELECT p FROM Planification p  WHERE p.specialty.id = :specialtyId AND p.date > :date AND p.currentcapacity < p.capacity")
    List<Planification> findAvailablePlanifications(Long specialtyId,Date date);
    List<Planification> findPlanificationBySpecialtyAndAndDate(Specialty specialty , Date date);
    List<Planification> findPlanificationByDoctorsAndDateBetween(Doctor doctor,Date datestart,Date dateend);
    Page<Planification> findPlanificationBySpecialtyAndDateBetween(
            PageRequest pageRequest , Specialty specialty , Date datestart , Date daternd);
    Page<Planification> findPlanificationByDateBetween(
            PageRequest pageRequest , Date datestart , Date daternd);
    Page<Planification> findPlanificationByDateBetweenAndSpecialty(
            PageRequest pageRequest , Specialty specialty , Date datestart , Date daternd);


}
