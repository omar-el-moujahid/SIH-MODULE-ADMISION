package ma.ensa.sihmoduleadmission.repos;

import ma.ensa.sihmoduleadmission.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
}
