package ma.ensa.sihmoduleadmission.repos;

import ma.ensa.sihmoduleadmission.entities.Appointment;
import ma.ensa.sihmoduleadmission.entities.Doctor;
import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
    @Query("SELECT a.patient FROM Appointment a WHERE a.specialty = :specialty AND a.dateofRDV = :date AND a.patient = :patient")
    Patient findPatient(Specialty specialty, Date date, Patient patient);
    @Query("SELECT a FROM Appointment a WHERE a.patient = :patient AND a.dateofRDV > :date ORDER BY a.dateofRDV")
    List<Appointment> findAppointmentByPatientAndAndDateofRDVAfter( @Param("patient") Patient patient,
                                                                    @Param("date") Date date );
    List<Appointment>  findAppointmentByDoctorAndSpecialtyAndDateofRDV(Doctor doctor , Specialty specialty ,Date date);
    List<Appointment> findAppointmentByDoctorAndSpecialtyAndDateofRDVEqualsAndAnnuleFalseAndIspasseFalse(Doctor doctor , Specialty specialty , Date date);

}
