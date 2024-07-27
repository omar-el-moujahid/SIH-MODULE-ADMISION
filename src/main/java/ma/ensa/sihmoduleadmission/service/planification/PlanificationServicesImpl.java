package ma.ensa.sihmoduleadmission.service.planification;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.dto.AddPlanificationDTO;
import ma.ensa.sihmoduleadmission.dto.PlanificationDTO;
import ma.ensa.sihmoduleadmission.entities.Appointment;
import ma.ensa.sihmoduleadmission.entities.Doctor;
import ma.ensa.sihmoduleadmission.entities.Planification;
import ma.ensa.sihmoduleadmission.entities.Specialty;
import ma.ensa.sihmoduleadmission.expetion.ApiRequestExpetion;
import ma.ensa.sihmoduleadmission.mapper.SIHMapper;
import ma.ensa.sihmoduleadmission.repos.PlanificationRepo;
import ma.ensa.sihmoduleadmission.repos.SpecialtyRepo;
import ma.ensa.sihmoduleadmission.service.appointment.AppointmentService;
import ma.ensa.sihmoduleadmission.service.doctor.DoctorServices;
import ma.ensa.sihmoduleadmission.service.patient.PatientServices;
import ma.ensa.sihmoduleadmission.service.sendemail.EmailSende;
import ma.ensa.sihmoduleadmission.service.speciality.SpecialtyServices;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class PlanificationServicesImpl implements PlanificationServices {
    private final SpecialtyRepo specialtyRepo;
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
                                     PatientServices patientServicesImpl, SpecialtyRepo specialtyRepo) {
        this.specialtyServicesImpl = specialtyServicesImpl;
        this.planificationRepo = planificationRepo;
        this.sihMapper = sihMapper;
        this.emailSende = emailSende;
        this.appointmentServiceimp = appointmentServiceimp;
        this.doctorServicesImpl = doctorServicesImpl;
        this.patientServicesImpl = patientServicesImpl;
        this.specialtyRepo = specialtyRepo;
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
    @Override
    public List<PlanificationDTO> findByDocotorAndDateBetween(String cne, int intel) {
        Doctor doctor = doctorServicesImpl.findbyid(cne);
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, intel); // Assuming interval is in days
        Date endDate = calendar.getTime();
        List<Planification> planifications = planificationRepo.
                findPlanificationByDoctorsAndDateBetween(doctor, date, endDate);
        List<PlanificationDTO> planificationDTOS =
                planifications.stream().map(planification ->
                        sihMapper.PlanificationToDTOPlanification(planification))
                        .collect(Collectors.toList());
        return planificationDTOS ;
    }
    @Override
    public Page<PlanificationDTO> findplanificationByDateBetween(Date startDate, Date endDate, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.asc("date")));
        Page<Planification> planification = planificationRepo.findPlanificationByDateBetween(PageRequest.of(page, 10), startDate, endDate);
        Page<PlanificationDTO> map = planification.map(planification1 -> sihMapper.PlanificationToDTOPlanification(planification1));
        return map;
    }
    @Override
    public Page<PlanificationDTO> findPlanificationBySpecialtiesAndAndDateBetween(Specialty speciality, Date startDate, Date endDate, int page) {
        Page<Planification> planification = planificationRepo.findPlanificationByDateBetweenAndSpecialty(
                PageRequest.of(page, 10), speciality ,startDate, endDate);
        Page<PlanificationDTO> map = planification.map(planification1 -> sihMapper.PlanificationToDTOPlanification(planification1));
        return map;
    }
    @Override
    public void delete(Long id){
        planificationRepo.deleteById(id);
    }
    public void addplaning(AddPlanificationDTO dto) {
        Planification planification = new Planification();
        BeanUtils.copyProperties(dto, planification);

        if (dto.getSpecialityName() == null || dto.getSpecialityName().isEmpty()) {
            throw new ApiRequestExpetion("Speciality name cannot be null or empty");
        }

        Specialty specialty = specialtyServicesImpl.findbyname(dto.getSpecialityName());
        if (specialty == null) {
            throw new ApiRequestExpetion("No specialty found for name: " + dto.getSpecialityName());
        }
        planification.setCurrentcapacity(0);
        planification.setTotalePatientAttend(0);
        planification.setTotalePatientMissed(0);
        planification.setSpecialty(specialty);
        planificationRepo.save(planification);
    }

}
