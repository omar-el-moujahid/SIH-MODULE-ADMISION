package ma.ensa.sihmoduleadmission.web;

import jakarta.validation.Valid;
import ma.ensa.sihmoduleadmission.dto.DoctorDTO;
import ma.ensa.sihmoduleadmission.entities.Doctor;
import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.expetion.thrabelauthontification;
import ma.ensa.sihmoduleadmission.service.appointment.AppointmentService;
import ma.ensa.sihmoduleadmission.service.appointment.AppointmentServiceImpl;
import ma.ensa.sihmoduleadmission.service.doctor.DoctorServices;
import ma.ensa.sihmoduleadmission.service.doctor.DoctorServicesImpl;
import ma.ensa.sihmoduleadmission.service.medicale_history.MedicalHistoryServiceImpl;
import ma.ensa.sihmoduleadmission.service.medicale_history.Medical_HistoryService;
import ma.ensa.sihmoduleadmission.service.patient.PatientServices;
import ma.ensa.sihmoduleadmission.service.patient.PatientServicesImpl;
import ma.ensa.sihmoduleadmission.service.planification.PlanificationServices;
import ma.ensa.sihmoduleadmission.service.planification.PlanificationServicesImpl;
import ma.ensa.sihmoduleadmission.service.speciality.SpecialtyServices;
import ma.ensa.sihmoduleadmission.service.speciality.SpecialtyServicesImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Validated
@RestController
public class DoctorRestControleur {
    private PatientServices patientServicesImpl;
    private DoctorServices doctorServicesImpl;
    private Medical_HistoryService medicalHistoryServiceImpl;
    private AppointmentService appointmentServiceImpl;
    private PlanificationServices planificationServicesImpl;
    private SpecialtyServices specialtyServicesImpl;

    public DoctorRestControleur(PatientServices patientServicesImpl, DoctorServices doctorServicesImpl, AppointmentService appointmentServiceImpl, Medical_HistoryService medicalHistoryServiceImpl, PlanificationServices planificationServicesImpl, SpecialtyServices specialtyServicesImpl) {
        this.patientServicesImpl = patientServicesImpl;
        this.doctorServicesImpl = doctorServicesImpl;
        this.appointmentServiceImpl = appointmentServiceImpl;
        this.medicalHistoryServiceImpl = medicalHistoryServiceImpl;
        this.planificationServicesImpl = planificationServicesImpl;
        this.specialtyServicesImpl = specialtyServicesImpl;
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
    @GetMapping("/doctor/getAll/{page}")
    public Page<DoctorDTO> getAllDoctors(@PathVariable(name = "page") int page){
        return doctorServicesImpl.getAllDoctors(page);
    }
    @GetMapping("/doctor/getAllBySpeciality")
    public Page<DoctorDTO> getAllBySpeciality(@RequestParam int page , @RequestParam String specialityName){
        return doctorServicesImpl.getAllDoctorsBySpeciality(page,specialityName);
    }
    @DeleteMapping("/doctor/DeleteDoctor/{cin}")
    public void deletDocctor(@PathVariable(name = "cin") String cin){
         doctorServicesImpl.delete(cin);
    }
    @GetMapping("/doctor/getDoctorBycIN/{cin}")
    public Page<DoctorDTO> getDoctorBycIN(@PathVariable(name = "cin") String cin ){
        return doctorServicesImpl.findbycin(cin);
    }
    @PostMapping("/doctor/save")
    public void save(@Valid @RequestBody DoctorDTO doctorDTO){
         doctorServicesImpl.save(doctorDTO);
    }
}
