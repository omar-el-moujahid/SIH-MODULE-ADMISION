package ma.ensa.sihmoduleadmission.web;

import ma.ensa.sihmoduleadmission.repos.PlanificationRepo;
import ma.ensa.sihmoduleadmission.service.appointment.AppointmentServiceImpl;
import ma.ensa.sihmoduleadmission.service.doctor.DoctorServicesImpl;
import ma.ensa.sihmoduleadmission.service.medicale_history.MedicalHistoryServiceImpl;
import ma.ensa.sihmoduleadmission.service.patient.PatientServicesImpl;
import ma.ensa.sihmoduleadmission.service.planification.PlanificationServicesImpl;
import ma.ensa.sihmoduleadmission.service.speciality.SpecialtyServicesImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Validated
@RestController

public class PlanificationRestControleur {
    private final PlanificationRepo planificationRepo;
    private PatientServicesImpl patientServicesImpl;
    private DoctorServicesImpl doctorServicesImpl;
    private MedicalHistoryServiceImpl medicalHistoryServiceImpl;
    private AppointmentServiceImpl appointmentServiceImpl;
    private PlanificationServicesImpl planificationServicesImpl;
    private SpecialtyServicesImpl specialtyServicesImpl;

    public PlanificationRestControleur(PatientServicesImpl patientServices, DoctorServicesImpl doctorServices,
                                       MedicalHistoryServiceImpl medicalHistoryService, AppointmentServiceImpl appointmentService,
                                       PlanificationServicesImpl planificationServices, SpecialtyServicesImpl specialtyServices, PlanificationRepo planificationRepo) {
        this.patientServicesImpl = patientServices;
        this.doctorServicesImpl = doctorServices;
        this.medicalHistoryServiceImpl = medicalHistoryService;
        this.appointmentServiceImpl = appointmentService;
        this.planificationServicesImpl = planificationServices;
        this.specialtyServicesImpl = specialtyServices;
        this.planificationRepo = planificationRepo;
    }
    @PostMapping("chu/planificaion/increment/{id}")
    public void incrementplanificaion(@PathVariable(name = "id") Long id){
        planificationServicesImpl.increment(id);
    }
    @PostMapping("chu/planificaion/desincrement/{id}")
    public void desincrementplanificaion(@PathVariable(name = "id") Long id){
        planificationServicesImpl.desincrement(id);
    }
}
