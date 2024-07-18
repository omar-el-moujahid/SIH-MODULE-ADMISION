package ma.ensa.sihmoduleadmission.web;

import ma.ensa.sihmoduleadmission.service.appointment.AppointmentServiceImpl;
import ma.ensa.sihmoduleadmission.service.doctor.DoctorServicesImpl;
import ma.ensa.sihmoduleadmission.service.medicale_history.MedicalHistoryServiceImpl;
import ma.ensa.sihmoduleadmission.service.patient.PatientServicesImpl;
import ma.ensa.sihmoduleadmission.service.planification.PlanificationServicesImpl;
import ma.ensa.sihmoduleadmission.service.speciality.SpecialtyServicesImpl;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanificationRestControleur {
    private PatientServicesImpl patientServicesImpl;
    private DoctorServicesImpl doctorServicesImpl;
    private MedicalHistoryServiceImpl medicalHistoryServiceImpl;
    private AppointmentServiceImpl appointmentServiceImpl;
    private PlanificationServicesImpl planificationServicesImpl;
    private SpecialtyServicesImpl specialtyServicesImpl;

    public PlanificationRestControleur(PatientServicesImpl patientServices, DoctorServicesImpl doctorServices,
                  MedicalHistoryServiceImpl medicalHistoryService, AppointmentServiceImpl appointmentService,
                  PlanificationServicesImpl planificationServices, SpecialtyServicesImpl specialtyServices) {
        this.patientServicesImpl = patientServices;
        this.doctorServicesImpl = doctorServices;
        this.medicalHistoryServiceImpl = medicalHistoryService;
        this.appointmentServiceImpl = appointmentService;
        this.planificationServicesImpl = planificationServices;
        this.specialtyServicesImpl = specialtyServices;
    }
}
