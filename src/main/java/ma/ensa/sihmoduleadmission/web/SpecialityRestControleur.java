package ma.ensa.sihmoduleadmission.web;

import ma.ensa.sihmoduleadmission.dto.SpecialtyDTO;
import ma.ensa.sihmoduleadmission.entities.Specialty;
import ma.ensa.sihmoduleadmission.service.medicale_history.MedicalHistoryServiceImpl;
import ma.ensa.sihmoduleadmission.service.appointment.AppointmentServiceImpl;
import ma.ensa.sihmoduleadmission.service.doctor.DoctorServicesImpl;
import ma.ensa.sihmoduleadmission.service.patient.PatientServicesImpl;
import ma.ensa.sihmoduleadmission.service.planification.PlanificationServicesImpl;
import ma.ensa.sihmoduleadmission.service.speciality.SpecialtyServicesImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@Validated
public class SpecialityRestControleur {
    private PatientServicesImpl patientServicesImpl;
    private DoctorServicesImpl doctorServicesImpl;
    private MedicalHistoryServiceImpl medicalHistoryServiceImpl;
    private AppointmentServiceImpl appointmentServiceImpl;
    private PlanificationServicesImpl planificationServicesImpl;
    private SpecialtyServicesImpl specialtyServicesImpl;

    public SpecialityRestControleur(PatientServicesImpl patientServices, DoctorServicesImpl doctorServices,
                  MedicalHistoryServiceImpl medicalHistoryService, AppointmentServiceImpl appointmentService,
                  PlanificationServicesImpl planificationServices, SpecialtyServicesImpl specialtyServices) {
        this.patientServicesImpl = patientServices;
        this.doctorServicesImpl = doctorServices;
        this.medicalHistoryServiceImpl = medicalHistoryService;
        this.appointmentServiceImpl = appointmentService;
        this.planificationServicesImpl = planificationServices;
        this.specialtyServicesImpl = specialtyServices;
    }
    @GetMapping("/specialities")
    public List<SpecialtyDTO> specialtyDTOS() {
        List<SpecialtyDTO> specialties = specialtyServicesImpl.findAll();
        return specialtyServicesImpl.findAll();
    }
    @GetMapping("/specialities/Count")
    public Long countUser( @RequestParam(required = false) String SpecialityName) {
        if(SpecialityName==null || SpecialityName.isEmpty()) return specialtyServicesImpl.count();
        return specialtyServicesImpl.countBySpeciality(SpecialityName);
    }
}
