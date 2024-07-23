package ma.ensa.sihmoduleadmission.web;

import ma.ensa.sihmoduleadmission.dto.AppointmentDTO;
import ma.ensa.sihmoduleadmission.dto.AppointmentDTOForDoctor;
import ma.ensa.sihmoduleadmission.entities.Appointment;
import ma.ensa.sihmoduleadmission.service.appointment.AppointmentServiceImpl;
import ma.ensa.sihmoduleadmission.service.doctor.DoctorServicesImpl;
import ma.ensa.sihmoduleadmission.service.medicale_history.MedicalHistoryServiceImpl;
import ma.ensa.sihmoduleadmission.service.patient.PatientServicesImpl;
import ma.ensa.sihmoduleadmission.service.planification.PlanificationServicesImpl;
import ma.ensa.sihmoduleadmission.service.speciality.SpecialtyServicesImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@Validated
public class AppointmentRestControleur {
    private PatientServicesImpl patientServicesImpl;
    private DoctorServicesImpl doctorServicesImpl;
    private MedicalHistoryServiceImpl medicalHistoryServiceImpl;
    private AppointmentServiceImpl appointmentServiceImpl;
    private PlanificationServicesImpl planificationServicesImpl;
    private SpecialtyServicesImpl specialtyServicesImpl;

    public AppointmentRestControleur(PatientServicesImpl patientServices, DoctorServicesImpl doctorServices,
                  MedicalHistoryServiceImpl medicalHistoryService, AppointmentServiceImpl appointmentService,
                  PlanificationServicesImpl planificationServices, SpecialtyServicesImpl specialtyServices) {
        this.patientServicesImpl = patientServices;
        this.doctorServicesImpl = doctorServices;
        this.medicalHistoryServiceImpl = medicalHistoryService;
        this.appointmentServiceImpl = appointmentService;
        this.planificationServicesImpl = planificationServices;
        this.specialtyServicesImpl = specialtyServices;
    }
    @GetMapping("chu/patient/appointment")
    Appointment appointment( @RequestParam String SpecialityName , @RequestParam String PatientId){
        return appointmentServiceImpl.save(SpecialityName,PatientId);
    }
    @GetMapping("chu/patient/appointment/{patientId}")
    List<AppointmentDTO> paientappointment(@PathVariable(name = "patientId") String patientId){
        return appointmentServiceImpl.PatiemtAppointmeent(patientId);
    }
    @DeleteMapping("chu/patient/deleteappointment/{appointmentId}")
    void deleteAppointement(@PathVariable(name = "appointmentId") Long id){
        appointmentServiceImpl.deleteappointement(id);
    }
    @GetMapping("chu/doctor/TodaySAppointment")
    List<AppointmentDTOForDoctor> TodaySAppointment(@RequestParam String CINDocotor , String SpecialityName){
       return appointmentServiceImpl.TodaySAppointment(CINDocotor,SpecialityName);
    }
}
