package ma.ensa.sihmoduleadmission.web;

import jakarta.validation.Valid;
import ma.ensa.sihmoduleadmission.dto.PatientDTO;
import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.expetion.thrabelauthontification;
import ma.ensa.sihmoduleadmission.mapper.SIHMapper;
import ma.ensa.sihmoduleadmission.service.appointment.AppointmentServiceImpl;
import ma.ensa.sihmoduleadmission.service.doctor.DoctorServicesImpl;
import ma.ensa.sihmoduleadmission.service.medicale_history.MedicalHistoryServiceImpl;
import ma.ensa.sihmoduleadmission.service.patient.PatientServicesImpl;
import ma.ensa.sihmoduleadmission.service.planification.PlanificationServicesImpl;
import ma.ensa.sihmoduleadmission.service.speciality.SpecialtyServicesImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Validated
public class PatientRestControleur {
    private PatientServicesImpl patientServicesImpl;
    private DoctorServicesImpl doctorServicesImpl;
    private MedicalHistoryServiceImpl medicalHistoryServiceImpl;
    private AppointmentServiceImpl appointmentServiceImpl;
    private PlanificationServicesImpl planificationServicesImpl;
    private SpecialtyServicesImpl specialtyServicesImpl;
    private SIHMapper sihMapper;
    private PasswordEncoder passwordEncoder;

    public PatientRestControleur(PatientServicesImpl patientServices, DoctorServicesImpl doctorServices,
                                 MedicalHistoryServiceImpl medicalHistoryService, AppointmentServiceImpl appointmentService,
                                 PlanificationServicesImpl planificationServices, SpecialtyServicesImpl specialtyServicesImpl, SIHMapper sihMapper, PasswordEncoder passwordEncoder) {
        this.patientServicesImpl = patientServices;
        this.doctorServicesImpl = doctorServices;
        this.medicalHistoryServiceImpl = medicalHistoryService;
        this.appointmentServiceImpl = appointmentService;
        this.planificationServicesImpl = planificationServices;
        this.specialtyServicesImpl = specialtyServicesImpl;
        this.sihMapper = sihMapper;
        this.passwordEncoder = passwordEncoder;
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String,String > handelmethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error)->{
//            String fieldname=((FieldError)error).getField();
//            String errorMessage=error.getDefaultMessage();
//            errors.put(fieldname,errorMessage);
//        });
//        return errors;
//    }

    @GetMapping("/patient")
    public List<PatientDTO> displaypatient(){
        return patientServicesImpl.findAll();
    }
    @GetMapping("/patient/login")
    public Patient authenticatePatient(@RequestParam String CNI, @RequestParam String password) throws thrabelauthontification {
        return patientServicesImpl.authentication(CNI, password);
    }
    @GetMapping("/patient/{id}")
    public PatientDTO authenticatePatient(@PathVariable(name = "id") String id) {
        return patientServicesImpl.find(id);
    }

    @PostMapping("/patient/addPatient")
    public void savePatient(@Valid @RequestBody PatientDTO patientDTO) {
        Patient patient = sihMapper.PatientDTOToPatient(patientDTO);
        patient.setPassword(passwordEncoder.encode(patientDTO.getPassword()));
        patientServicesImpl.save(patient);
    }

    @PostMapping("/patient/Updatepassword")
    public ResponseEntity<String> updatePassword(@RequestParam String CIN, @RequestParam String Oldpassword, @RequestParam String Newpassword) {
        patientServicesImpl.Updatepassword(CIN, Oldpassword, Newpassword);
        return ResponseEntity.ok("Password updated successfully");
    }

}

