package ma.ensa.sihmoduleadmission.service.appointment;

import ma.ensa.sihmoduleadmission.entities.Appointment;
import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.entities.Specialty;

import java.util.Date;

public interface AppointmentService {
    Appointment save(Appointment appointment);
    Appointment save(String SpecialityName,String PatientId);
    void DidPatientAlreadyTakeAppointment(Specialty specialty , Date date, Patient patient);
}
