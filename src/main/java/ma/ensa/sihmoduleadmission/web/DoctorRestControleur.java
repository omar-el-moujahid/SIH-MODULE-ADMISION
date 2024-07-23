package ma.ensa.sihmoduleadmission.web;

import ma.ensa.sihmoduleadmission.dto.DoctorDTO;
import ma.ensa.sihmoduleadmission.entities.Doctor;
import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.expetion.thrabelauthontification;
import ma.ensa.sihmoduleadmission.service.appointment.AppointmentServiceImpl;
import ma.ensa.sihmoduleadmission.service.doctor.DoctorServicesImpl;
import ma.ensa.sihmoduleadmission.service.medicale_history.MedicalHistoryServiceImpl;
import ma.ensa.sihmoduleadmission.service.patient.PatientServicesImpl;
import ma.ensa.sihmoduleadmission.service.planification.PlanificationServicesImpl;
import ma.ensa.sihmoduleadmission.service.speciality.SpecialtyServicesImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Validated
@RestController
public class DoctorRestControleur {
    private PatientServicesImpl patientServicesImpl;
    private DoctorServicesImpl doctorServicesImpl;
    private MedicalHistoryServiceImpl medicalHistoryServiceImpl;
    private AppointmentServiceImpl appointmentServiceImpl;
    private PlanificationServicesImpl planificationServicesImpl;
    private SpecialtyServicesImpl specialtyServicesImpl;

    public DoctorRestControleur(PatientServicesImpl patientServices, DoctorServicesImpl doctorServices,
                  MedicalHistoryServiceImpl medicalHistoryService, AppointmentServiceImpl appointmentService,
                  PlanificationServicesImpl planificationServices, SpecialtyServicesImpl specialtyServices) {
        this.patientServicesImpl = patientServices;
        this.doctorServicesImpl = doctorServices;
        this.medicalHistoryServiceImpl = medicalHistoryService;
        this.appointmentServiceImpl = appointmentService;
        this.planificationServicesImpl = planificationServices;
        this.specialtyServicesImpl = specialtyServices;
    }
    @GetMapping("/doctor/login")
    public DoctorDTO authenticatePatient(@RequestParam String CNI, @RequestParam String password) throws thrabelauthontification {
        return doctorServicesImpl.authentication(CNI, password);
    }
    @PostMapping("/doctor/Updatepassword")
    public ResponseEntity<String> updatePassword(@RequestParam String CIN, @RequestParam String Oldpassword, @RequestParam String Newpassword) {
        doctorServicesImpl.Updatepassword(CIN, Oldpassword, Newpassword);
        return ResponseEntity.ok("Password updated successfully");
    }
}
