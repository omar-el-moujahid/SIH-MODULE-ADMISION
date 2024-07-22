package ma.ensa.sihmoduleadmission.repos;

import ma.ensa.sihmoduleadmission.entities.Appointment;
import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
    @Query("SELECT a.patient FROM Appointment a WHERE a.specialty = :specialty AND a.dateofRDV = :date AND a.patient = :patient")
    Patient findPatient(Specialty specialty, Date date, Patient patient);
    List<Appointment> findAppointmentByPatientAndAndDateofRDVAfter(Patient patient, Date date);
}
