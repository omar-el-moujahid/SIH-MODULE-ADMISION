package ma.ensa.sihmoduleadmission.repos;

import ma.ensa.sihmoduleadmission.entities.Appointment;
import ma.ensa.sihmoduleadmission.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor,String> {
}
