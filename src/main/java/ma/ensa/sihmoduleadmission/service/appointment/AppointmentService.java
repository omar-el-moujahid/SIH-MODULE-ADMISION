package ma.ensa.sihmoduleadmission.service.appointment;

import ma.ensa.sihmoduleadmission.dto.AppointmentDTO;
import ma.ensa.sihmoduleadmission.dto.AppointmentDTOForDoctor;
import ma.ensa.sihmoduleadmission.entities.Appointment;
import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.entities.Specialty;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AppointmentService {
    Appointment save(Appointment appointment);
    Appointment save(String SpecialityName,String PatientId);
    void DidPatientAlreadyTakeAppointment(Specialty specialty , Date date, Patient patient);
    List<AppointmentDTO> PatiemtAppointmeent(String id);
    void deleteappointement(Long id);
    List<AppointmentDTOForDoctor> TodaySAppointment(String cinDocotor, String specialityName);
    Appointment findbyid(Long id);

    Map<String, Long> countPatientsByMonth(int year);
}
