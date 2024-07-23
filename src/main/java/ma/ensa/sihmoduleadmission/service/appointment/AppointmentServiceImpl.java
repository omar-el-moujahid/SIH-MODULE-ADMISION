package ma.ensa.sihmoduleadmission.service.appointment;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.dto.AppointmentDTO;
import ma.ensa.sihmoduleadmission.dto.AppointmentDTOForDoctor;
import ma.ensa.sihmoduleadmission.entities.*;
import ma.ensa.sihmoduleadmission.expetion.ApiRequestExpetion;
import ma.ensa.sihmoduleadmission.mapper.SIHMapper;
import ma.ensa.sihmoduleadmission.repos.AppointmentRepo;
import ma.ensa.sihmoduleadmission.service.doctor.DoctorServices;
import ma.ensa.sihmoduleadmission.service.patient.PatientServices;
import ma.ensa.sihmoduleadmission.service.planification.PlanificationServices;
import ma.ensa.sihmoduleadmission.service.sendemail.EmailSende;
import ma.ensa.sihmoduleadmission.service.speciality.SpecialtyServices;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
    private SpecialtyServices specialtyServicesImpl;
    private PlanificationServices planificationServicesImpl;
    private PatientServices patientServicesImpl;
    private DoctorServices doctorServicesImpl;
    private AppointmentRepo appointmentRepo;
    private PlanificationServices planificationServices;
    private SIHMapper sihMapper;
    private  EmailSende emailSende;

    public AppointmentServiceImpl(SpecialtyServices specialtyServicesImpl,
                                  @Lazy PlanificationServices planificationServicesImpl,
                                  PatientServices patientServicesImpl, DoctorServices doctorServicesImpl,
                                  AppointmentRepo appointmentRepo, PlanificationServices planificationServices,
                                  SIHMapper sihMapper, EmailSende emailSende) {
        this.specialtyServicesImpl = specialtyServicesImpl;
        this.planificationServicesImpl = planificationServicesImpl;
        this.patientServicesImpl = patientServicesImpl;
        this.doctorServicesImpl = doctorServicesImpl;
        this.appointmentRepo = appointmentRepo;
        this.planificationServices = planificationServices;
        this.sihMapper = sihMapper;
        this.emailSende = emailSende;
    }

    @Override
    public Appointment save(Appointment appointment) {
        appointmentRepo.save(appointment);
        return appointment;
    }

    @Override
    public Appointment save(String SpecialityName , String PatientId) {
        Specialty specialty = specialtyServicesImpl.findbyname(SpecialityName);
// Fetch available planifications for the given specialty
        List<Planification> planifications = planificationServicesImpl.findavalabel(specialty);
// Check if planifications list is empty
        if (planifications.isEmpty()) {
            throw new ApiRequestExpetion("No available planifications for the selected specialty.");
        }
// Create a new appointment
        Appointment appointment = new Appointment();
        appointment.setAnnule(false);
        appointment.setSpecialty(specialty);
        appointment.setIspasse(false);
        appointment.setDateofRDV(planifications.get(0).getDate());
        appointment.setDoctor(planifications.get(0).getDoctors().get(0));

// Fetch patient by ID
        Patient patient = patientServicesImpl.findbyid(PatientId);
// Check if patient is null
        if (patient == null) {
            throw new ApiRequestExpetion("Patient with ID "+PatientId+ " does not exist.");
        }
        this.DidPatientAlreadyTakeAppointment(specialty,planifications.get(0).getDate(),patient);
        appointment.setPatient(patient);
// Save the appointment
        Appointment appointment1 = appointmentRepo.save(appointment);
        planifications.get(0).setCurrentcapacity(planifications.get(0).getCurrentcapacity()+1);
        String to = patient.getMail();
        String subject = "Appointment Confirmation";
        String body = "Your appointment has been successfully scheduled at the "+planifications.get(0).getDate() +" in " +
                "the "+specialty.getSpecialtyName() ;
        emailSende.setJavaMailSender(to, subject, body);
        return appointment1;
    }

    @Override
    public void DidPatientAlreadyTakeAppointment(Specialty specialty, Date date, Patient patient) {
        Patient existingPatient = appointmentRepo.findPatient(specialty, date, patient);
        if (existingPatient != null) {
            throw new ApiRequestExpetion("Patient"+patient.getCNE()+
                    "already has an appointement in "+date+" in this speciality");
        }
    }

    @Override
    public List<AppointmentDTO> PatiemtAppointmeent(String id) {
        Patient patient = patientServicesImpl.findbyid(id);
        List<Appointment> appointments = appointmentRepo.findAppointmentByPatientAndAndDateofRDVAfter(patient, new Date());
        List<AppointmentDTO> appointmentDTOS = appointments.stream().map
                        (appointment -> sihMapper.AppointmentToDTOAppointment(appointment))
                .collect(Collectors.toList());
        return appointmentDTOS ;
    }

    @Override
    public void deleteappointement(Long id) {
        Appointment byId = appointmentRepo.findById(id).orElseThrow(()->new ApiRequestExpetion("appointment not found"));
        appointmentRepo.deleteById(id);
        Planification planification = planificationServices.findPlanificationBySpecialtiesAndAndDate(byId.getSpecialty(), byId.getDateofRDV());
        planification.setCurrentcapacity(planification.getCurrentcapacity()-1);
        planificationServices.save(planification);
    }
    @Override
    public List<AppointmentDTOForDoctor> TodaySAppointment(String cinDocotor, String specialityName) {
        Doctor doctor = doctorServicesImpl.findbyid(cinDocotor);
        Specialty specialty = specialtyServicesImpl.findbyname(specialityName);
        List<Appointment> appointments =
                appointmentRepo.findAppointmentByDoctorAndSpecialtyAndDateofRDVEqualsAndAnnuleFalseAndIspasseFalse(doctor, specialty, new Date());
        List<AppointmentDTOForDoctor> appointmentDTOS = appointments.stream()
                .map(appointment -> sihMapper.AppointmentToAppointmentDTOForDoctor(appointment))
                .collect(Collectors.toList());
        return appointmentDTOS;
    }

    @Override
    public Appointment findbyid(Long id) {
        return appointmentRepo.findById(id).orElseThrow(() -> new ApiRequestExpetion("Appointment nof found"));
    }
}
