package ma.ensa.sihmoduleadmission;

import ma.ensa.sihmoduleadmission.dto.PatientDTO;
import ma.ensa.sihmoduleadmission.entities.*;
import ma.ensa.sihmoduleadmission.expetion.ApiRequestExpetion;
import ma.ensa.sihmoduleadmission.mapper.SIHMapper;
import ma.ensa.sihmoduleadmission.service.appointment.AppointmentServiceImpl;
import ma.ensa.sihmoduleadmission.service.doctor.DoctorServicesImpl;
import ma.ensa.sihmoduleadmission.service.medicale_history.MedicalHistoryServiceImpl;
import ma.ensa.sihmoduleadmission.service.patient.PatientServicesImpl;
import ma.ensa.sihmoduleadmission.service.planification.PlanificationServicesImpl;
import ma.ensa.sihmoduleadmission.service.role.RoleServicesImpl;
import ma.ensa.sihmoduleadmission.service.speciality.SpecialtyServicesImpl;
import org.apache.catalina.mapper.Mapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//@Component
public class DBInit implements ApplicationRunner {
    private PatientServicesImpl  patientServicesImpl ;
    private DoctorServicesImpl doctorServicesImpl;
    private MedicalHistoryServiceImpl medicalHistoryServiceImpl;
    private AppointmentServiceImpl appointmentServiceImpl;
    private PlanificationServicesImpl planificationServicesImpl;
    private SpecialtyServicesImpl specialtyServicesImpl;
    private RoleServicesImpl roleServicesImpl;
    private PasswordEncoder passwordEncoder;
    private SIHMapper sihMapper;

    public DBInit(DoctorServicesImpl doctorServicesImpl, PatientServicesImpl patientServicesImpl,
                  MedicalHistoryServiceImpl medicalHistoryServiceImpl, AppointmentServiceImpl appointmentServiceImpl,
                  PlanificationServicesImpl planificationServicesImpl, SpecialtyServicesImpl specialtyServicesImpl,
                  RoleServicesImpl roleServicesImpl, PasswordEncoder passwordEncoder, SIHMapper sihMapper) {
        this.doctorServicesImpl = doctorServicesImpl;
        this.patientServicesImpl = patientServicesImpl;
        this.medicalHistoryServiceImpl = medicalHistoryServiceImpl;
        this.appointmentServiceImpl = appointmentServiceImpl;
        this.planificationServicesImpl = planificationServicesImpl;
        this.specialtyServicesImpl = specialtyServicesImpl;
        this.roleServicesImpl = roleServicesImpl;
        this.passwordEncoder = passwordEncoder;
        this.sihMapper = sihMapper;
    }

//    @Override
    public void run(ApplicationArguments args) throws Exception {
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2024, Calendar.JULY, 28);
//        Specialty specialty=specialtyServicesImpl.findbyid(Long.valueOf(3));
//        Planification planification = new Planification();
//        planification.setCapacity(3);
//        planification.setDate(calendar.getTime());
//        List<Specialty> specialties = new ArrayList<>();
//        specialties.add(specialty);
//        planification.setSpecialties(specialties);
//
//        List<Doctor> doctors = new ArrayList<>();
//        doctors.add(doctorServicesImpl.findbyid("CD9180531"));
//        planification.setDoctors(doctors);
//        planification.setStartAt("8pm");
//        planification.setEndAt("8am");
//        planificationServicesImpl.save(planification);

//        Specialty specialty = specialtyServicesImpl.findbyid(1L);
//        List<Planification> planifications = planificationServicesImpl.findAvailable(specialty);
//        Appointment appointment = new Appointment();
//        appointment.setAnnule(false);
//        appointment.setSpecialty(specialty);
//        appointment.setIspasse(false);
//        appointment.setDateofRDV(planifications.get(0).getDate());
//        appointment.setDoctor(planifications.get(0).getDoctors().get(0));
//        PatientDTO patientDTO = patientServicesImpl.find("CD918057");
//        Patient patient = sihMapper.PatientDTOToPatient(patientDTO);
//        appointment.setPatient(patient);
//        appointmentServiceImpl.save(appointment);
        // Fetch specialty by ID
//        Specialty specialty = specialtyServicesImpl.findbyid(1L);
//
//// Fetch available planifications for the given specialty
//        List<Planification> planifications = planificationServicesImpl.findAvailable(specialty);
//
//// Check if planifications list is empty
//        if (planifications.isEmpty()) {
//            throw new ApiRequestExpetion("No available planifications for the selected specialty.");
//        }
//
//// Create a new appointment
//        Appointment appointment = new Appointment();
//        appointment.setAnnule(false);
//        appointment.setSpecialty(specialty);
//        appointment.setIspasse(false);
//        appointment.setDateofRDV(planifications.get(0).getDate());
//        appointment.setDoctor(planifications.get(0).getDoctors().get(0));
//
//// Fetch patient by ID
//        PatientDTO patientDTO = patientServicesImpl.find("CD918057");
//        Patient patient = sihMapper.PatientDTOToPatient(patientDTO);
//
//// Check if patient is null
//        if (patient == null) {
//            throw new ApiRequestExpetion("Patient with ID CD918057 does not exist.");
//        }
//
//        appointment.setPatient(patient);
//
//// Save the appointment
//        appointmentServiceImpl.save(appointment);


        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 2);
        Medical_History  medicalHistory = new Medical_History();
        medicalHistory.setDate(calendar.getTime());
        medicalHistory.setPatient(patientServicesImpl.findbyid("CD918057"));
        medicalHistory.setMedcineresponsable(doctorServicesImpl.findbyid("CD9180501"));
        medicalHistory.setMedicine("Paracetamol");
        medicalHistory.setDescription("Patient was treated for a mild fever.");
        medicalHistory.setThedisease("Mild Fever");
        medicalHistoryServiceImpl.save(medicalHistory);

        calendar.set(2023, Calendar.FEBRUARY, 15);
        medicalHistory = new Medical_History();
        medicalHistory.setDate(calendar.getTime());
        medicalHistory.setPatient(patientServicesImpl.findbyid("CD918057"));
        medicalHistory.setMedcineresponsable(doctorServicesImpl.findbyid("CD9180511"));
        medicalHistory.setMedicine("Amoxicillin");
        medicalHistory.setDescription("Patient was treated for a bacterial infection.");
        medicalHistory.setThedisease("Bacterial Infection");
        medicalHistory.setNotes("Did a radiographic image and blood analysis.");

        medicalHistoryServiceImpl.save(medicalHistory);

        calendar.set(2023, Calendar.APRIL, 10);
        medicalHistory = new Medical_History();
        medicalHistory.setDate(calendar.getTime());
        medicalHistory.setPatient(patientServicesImpl.findbyid("CD918057"));
        medicalHistory.setMedcineresponsable(doctorServicesImpl.findbyid("CD9180521"));
        medicalHistory.setMedicine("Lisinopril");
        medicalHistory.setDescription("Patient was treated for high blood pressure.");
        medicalHistory.setThedisease("Hypertension");

        // Save the fourth medical history entry
        medicalHistoryServiceImpl.save(medicalHistory);

    }
}
