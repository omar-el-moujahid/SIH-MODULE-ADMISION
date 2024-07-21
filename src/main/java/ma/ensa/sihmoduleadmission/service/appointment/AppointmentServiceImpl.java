package ma.ensa.sihmoduleadmission.service.appointment;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.dto.PatientDTO;
import ma.ensa.sihmoduleadmission.entities.Appointment;
import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.entities.Planification;
import ma.ensa.sihmoduleadmission.entities.Specialty;
import ma.ensa.sihmoduleadmission.expetion.ApiRequestExpetion;
import ma.ensa.sihmoduleadmission.mapper.SIHMapper;
import ma.ensa.sihmoduleadmission.repos.AppointmentRepo;
import ma.ensa.sihmoduleadmission.service.patient.PatientServicesImpl;
import ma.ensa.sihmoduleadmission.service.planification.PlanificationServicesImpl;
import ma.ensa.sihmoduleadmission.service.speciality.SpecialtyServicesImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
    private final SpecialtyServicesImpl specialtyServicesImpl;
    private final PlanificationServicesImpl planificationServicesImpl;
    private final PatientServicesImpl patientServicesImpl;
    private AppointmentRepo appointmentRepo;
    private SpecialtyServicesImpl specialtyServices;
    private PlanificationServicesImpl planificationServices;
    private SIHMapper sihMapper;
    public AppointmentServiceImpl(AppointmentRepo appointmentRepo, SpecialtyServicesImpl specialtyServices, PlanificationServicesImpl planificationServices, SpecialtyServicesImpl specialtyServicesImpl, PlanificationServicesImpl planificationServicesImpl, PatientServicesImpl patientServicesImpl, SIHMapper sihMapper) {
        this.appointmentRepo = appointmentRepo;
        this.specialtyServices = specialtyServices;
        this.planificationServices = planificationServices;
        this.specialtyServicesImpl = specialtyServicesImpl;
        this.planificationServicesImpl = planificationServicesImpl;
        this.patientServicesImpl = patientServicesImpl;
        this.sihMapper = sihMapper;
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
        Planification planifications = planificationServicesImpl.findAvailable(specialty);
// Create a new appointment
        Appointment appointment = new Appointment();
        appointment.setAnnule(false);
        appointment.setSpecialty(specialty);
        appointment.setIspasse(false);
        appointment.setDateofRDV(planifications.getDate());
        appointment.setDoctor(planifications.getDoctors().getFirst());

// Fetch patient by ID
        Patient patient = patientServicesImpl.findbyid(PatientId);
// Check if patient is null
        if (patient == null) {
            throw new ApiRequestExpetion("Patient with ID "+PatientId+ " does not exist.");
        }
        this.DidPatientAlreadyTakeAppointment(specialty,planifications.getDate(),patient );
        appointment.setPatient(patient);
// Save the appointment
        Appointment appointment1 = appointmentRepo.save(appointment);
        return appointment1;
    }

    @Override
    public void DidPatientAlreadyTakeAppointment(Specialty specialty, Date date, Patient patient) {
        Patient existingPatient = appointmentRepo.findPatient(specialty, date, patient);
        if (existingPatient != null) {
            throw new ApiRequestExpetion("Patient"+patient.getCNE()+
                    "already has an appointement in"+date+"in this speciality");
        }
    }
}
