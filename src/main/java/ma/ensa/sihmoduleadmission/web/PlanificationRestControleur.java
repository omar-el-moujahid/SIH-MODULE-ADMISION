package ma.ensa.sihmoduleadmission.web;

import jakarta.validation.Valid;
import ma.ensa.sihmoduleadmission.dto.AddPlanificationDTO;
import ma.ensa.sihmoduleadmission.dto.PlanificationDTO;
import ma.ensa.sihmoduleadmission.entities.Planification;
import ma.ensa.sihmoduleadmission.entities.Specialty;
import ma.ensa.sihmoduleadmission.expetion.ApiRequestExpetion;
import ma.ensa.sihmoduleadmission.mapper.SIHMapper;
import ma.ensa.sihmoduleadmission.repos.PlanificationRepo;
import ma.ensa.sihmoduleadmission.service.appointment.AppointmentServiceImpl;
import ma.ensa.sihmoduleadmission.service.doctor.DoctorServicesImpl;
import ma.ensa.sihmoduleadmission.service.medicale_history.MedicalHistoryServiceImpl;
import ma.ensa.sihmoduleadmission.service.patient.PatientServicesImpl;
import ma.ensa.sihmoduleadmission.service.planification.PlanificationServicesImpl;
import ma.ensa.sihmoduleadmission.service.speciality.SpecialtyServicesImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    private SIHMapper sihMapper ;

    public PlanificationRestControleur(PatientServicesImpl patientServices, DoctorServicesImpl doctorServices,
                                       MedicalHistoryServiceImpl medicalHistoryService, AppointmentServiceImpl appointmentService,
                                       PlanificationServicesImpl planificationServices, SpecialtyServicesImpl specialtyServices, PlanificationRepo planificationRepo, SIHMapper sihMapper) {
        this.patientServicesImpl = patientServices;
        this.doctorServicesImpl = doctorServices;
        this.medicalHistoryServiceImpl = medicalHistoryService;
        this.appointmentServiceImpl = appointmentService;
        this.planificationServicesImpl = planificationServices;
        this.specialtyServicesImpl = specialtyServices;
        this.planificationRepo = planificationRepo;
        this.sihMapper = sihMapper;
    }
    @PostMapping("chu/planificaion/increment/{id}")
    public void incrementplanificaion(@PathVariable(name = "id") Long id){
        planificationServicesImpl.increment(id);
    }
    @PostMapping("chu/planificaion/desincrement/{id}")
    public void desincrementplanificaion(@PathVariable(name = "id") Long id){
        planificationServicesImpl.desincrement(id);
    }
    @GetMapping("chu/planificaion/doctor")
    public List<PlanificationDTO> planificaionbydocotor(String cne , int intel){
       return planificationServicesImpl.findByDocotorAndDateBetween(cne,intel);
    }

    @GetMapping("chu/planificaion/getAll")
    public Page<PlanificationDTO> getAllPlanifications(
            @RequestParam(required = false) String speciality,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam int page) {

        if (startDate == null) {
            startDate = new Date(); // Default to current date
        }

        if (endDate == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.DAY_OF_YEAR, 60); // Default to 60 days from start date
            endDate = calendar.getTime();
        }

        if (speciality == null || speciality.equals("all")) {
            return planificationServicesImpl.findplanificationByDateBetween(startDate, endDate, page);
        } else {
            Specialty specialty = specialtyServicesImpl.findbyname(speciality);
            if (specialty == null) {
                throw new ApiRequestExpetion("Specialty not found");
            }
            return planificationServicesImpl.findPlanificationBySpecialtiesAndAndDateBetween(specialty, startDate, endDate, page);
        }
    }
    @DeleteMapping("chu/planificaion/Delete/{id}")
    public void delete(@PathVariable(name = "id") Long id ){
        planificationServicesImpl.delete(id);
    }
    @PostMapping("chu/planificaion/AddPlaning")
    public void addPlaning(@Valid @RequestBody AddPlanificationDTO dto) {
        planificationServicesImpl.addplaning(dto);
    }
}
