package ma.ensa.sihmoduleadmission.service.planification;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.entities.Appointment;
import ma.ensa.sihmoduleadmission.entities.Planification;
import ma.ensa.sihmoduleadmission.entities.Specialty;
import ma.ensa.sihmoduleadmission.expetion.ApiRequestExpetion;
import ma.ensa.sihmoduleadmission.mapper.SIHMapper;
import ma.ensa.sihmoduleadmission.repos.PlanificationRepo;
import ma.ensa.sihmoduleadmission.service.appointment.AppointmentService;
import ma.ensa.sihmoduleadmission.service.doctor.DoctorServices;
import ma.ensa.sihmoduleadmission.service.patient.PatientServices;
import ma.ensa.sihmoduleadmission.service.sendemail.EmailSende;
import ma.ensa.sihmoduleadmission.service.speciality.SpecialtyServices;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class PlanificationServicesImpl implements PlanificationServices {
    private SpecialtyServices specialtyServicesImpl;
    private PatientServices patientServicesImpl;
    private DoctorServices doctorServicesImpl;
    private AppointmentService appointmentServiceimp;
    private SIHMapper sihMapper;
    private EmailSende emailSende;
    private PlanificationRepo planificationRepo ;

    public PlanificationServicesImpl(SpecialtyServices specialtyServicesImpl, PlanificationRepo planificationRepo,
                                     SIHMapper sihMapper, EmailSende emailSende,
                                     @Lazy AppointmentService appointmentServiceimp, DoctorServices doctorServicesImpl,
                                     PatientServices patientServicesImpl) {
        this.specialtyServicesImpl = specialtyServicesImpl;
        this.planificationRepo = planificationRepo;
        this.sihMapper = sihMapper;
        this.emailSende = emailSende;
        this.appointmentServiceimp = appointmentServiceimp;
        this.doctorServicesImpl = doctorServicesImpl;
        this.patientServicesImpl = patientServicesImpl;
    }

    @Override
    public Planification save(Planification planification) {
        return planificationRepo.save(planification);
    }

    @Override
    public List <Planification> findavalabel(Specialty specialty) {
        List <Planification> planifications = planificationRepo.findAvailablePlanifications(specialty.getId(),new Date());
        if(planifications==null){
            throw new ApiRequestExpetion("No Appointement are avalabel for this speciality");
        }
        return planifications;
    }
    @Override
    public Planification findPlanificationBySpecialtiesAndAndDate(Specialty specialty, Date date){
        List <Planification> planification = planificationRepo.findPlanificationBySpecialtyAndAndDate(specialty, date);
        return planification.getFirst();
    }
    @Override
    public void delete(Planification planification) {
        planificationRepo.delete(planification);
    }
    @Override
    public void increment(Long id) {
        Appointment appointment = appointmentServiceimp.findbyid(id);
        appointment.setIspasse(true);
        appointmentServiceimp.save(appointment);
        List <Planification> planification = planificationRepo.findPlanificationBySpecialtyAndAndDate(appointment.getSpecialty(), appointment.getDateofRDV());
        if (planification.isEmpty()) {
            throw new ApiRequestExpetion("No planifications found for the given specialty and date.");
        }
        Planification firstPlanification = planification.get(0);
        firstPlanification.setTotalePatientAttend(firstPlanification.getTotalePatientAttend() + 1);
        planificationRepo.save(firstPlanification);
    }

    @Override
    public void desincrement(Long id) {
        Appointment appointment = appointmentServiceimp.findbyid(id);
        appointment.setAnnule(true);
        appointmentServiceimp.save(appointment);
        List <Planification> planification = planificationRepo.findPlanificationBySpecialtyAndAndDate(appointment.getSpecialty(), appointment.getDateofRDV());
        if (planification.isEmpty()) {
            throw new ApiRequestExpetion("No planifications found for the given specialty and date.");
        }
        Planification firstPlanification = planification.get(0);
        firstPlanification.setTotalePatientMissed(firstPlanification.getTotalePatientMissed() + 1);
        planificationRepo.save(firstPlanification);
    }
}
