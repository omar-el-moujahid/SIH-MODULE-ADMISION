package ma.ensa.sihmoduleadmission;

import ma.ensa.sihmoduleadmission.entities.Doctor;
import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.entities.Specialty;
import ma.ensa.sihmoduleadmission.entities.enums.Gender;
import ma.ensa.sihmoduleadmission.service.appointment.AppointmentServiceImpl;
import ma.ensa.sihmoduleadmission.service.doctor.DoctorServicesImpl;
import ma.ensa.sihmoduleadmission.service.medicale_history.MedicalHistoryServiceImpl;
import ma.ensa.sihmoduleadmission.service.patient.PatientServicesImpl;
import ma.ensa.sihmoduleadmission.service.planification.PlanificationServicesImpl;
import ma.ensa.sihmoduleadmission.service.speciality.SpecialtyServicesImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

//@Component
public class DBInit implements ApplicationRunner {
    private PatientServicesImpl patientServices;
    private DoctorServicesImpl doctorServices;
    private MedicalHistoryServiceImpl medicalHistoryService;
    private AppointmentServiceImpl appointmentService;
    private PlanificationServicesImpl planificationServices;
    private SpecialtyServicesImpl specialtyServices;
    private PasswordEncoder passwordEncoder;

    public DBInit(PatientServicesImpl patientServices, DoctorServicesImpl doctorServices,
                  MedicalHistoryServiceImpl medicalHistoryService, AppointmentServiceImpl appointmentService,
                  PlanificationServicesImpl planificationServices, SpecialtyServicesImpl specialtyServices, PasswordEncoder passwordEncoder) {
        this.patientServices = patientServices;
        this.doctorServices = doctorServices;
        this.medicalHistoryService = medicalHistoryService;
        this.appointmentService = appointmentService;
        this.planificationServices = planificationServices;
        this.specialtyServices = specialtyServices;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        for (int i = 0; i < 50; i++) {
//            Patient patient= Patient.builder().CNE("CD918057"+i)
//                    .adress("adress"+i)
//                    .mail("name"+i+"@gmail.com")
//                    .gender(Math.random() < 0.5 ? Gender.M:Gender.M)
//                    .dateofbirth(new Date())
//                    .contact("0600121212")
//                    .lastname("lastname"+i).firstname("firstname"+i)
//                    .build();
//            patientServices.save(patient);
//        }
//
//
//        Specialty specialty = Specialty.builder()
//                .specialtyName("Cardiologie")
//                .build();
//        specialtyServices.save(specialty);
//         specialty = Specialty.builder()
//                .specialtyName("Dermatologie")
//                .build();
//        specialtyServices.save(specialty);
//        specialty = Specialty.builder()
//                .specialtyName("Endocrinologie")
//                .build();
//        specialtyServices.save(specialty);
//        specialty = Specialty.builder()
//                .specialtyName("Gastro-entérologie")
//                .build();
//        specialtyServices.save(specialty);
//        specialty = Specialty.builder()
//                .specialtyName("Neurologie")
//                .build();
//        specialtyServices.save(specialty);
//        specialty = Specialty.builder()
//                .specialtyName("Ophtalmologie")
//                .build();
//        specialtyServices.save(specialty);
//        specialty = Specialty.builder()
//                .specialtyName("Orthopédie")
//                .build();
//        specialtyServices.save(specialty);
//        specialty = Specialty.builder()
//                .specialtyName("Pédiatrie")
//                .build();
//        specialtyServices.save(specialty);
//        specialty = Specialty.builder()
//                .specialtyName("Psychiatrie")
//                .build();
//        specialtyServices.save(specialty);
//        specialty = Specialty.builder()
//                .specialtyName("Pneumologie")
//                .build();
//        specialtyServices.save(specialty);
//        specialty = Specialty.builder()
//                .specialtyName("Radiologie")
//                .build();
//        specialtyServices.save(specialty);
//        specialty = Specialty.builder()
//                .specialtyName("Urologie")
//                .build();
//        specialtyServices.save(specialty);
//        specialty = Specialty.builder()
//                .specialtyName("Oncologie")
//                .build();
//        specialtyServices.save(specialty);
//        specialty = Specialty.builder()
//                .specialtyName("Néphrologie")
//                .build();
//        specialtyServices.save(specialty);
//        specialty = Specialty.builder()
//                .specialtyName("Rhumatologie")
//                .build();
//        specialtyServices.save(specialty);
//        for (int i = 0; i < 50; i++) {
//            Specialty specialty1 = specialtyServices.findbyid((long) (i % 10));
//            Doctor doctor= Doctor.builder().CNE("CD918057"+i)
//                    .adress("adress"+i)
//                    .mail("name"+i+"@gmail.com")
//                    .gender(Math.random() < 0.5 ? Gender.M:Gender.M)
//                    .dateofbirth(new Date())
//                    .contact("0600121212")
//                    .lastname("lastname"+i).firstname("firstname"+i).specialty(specialty1)
//                    .build();
//            doctorServices.save(doctor);
//        }
        Patient patient = new Patient();
        patient.setCNE("CC918057");
        patient.setFirstname("Omar");
        patient.setLastname("EL Moujahid");
        patient.setMail("omarelmoujahid@gmail.com");
        patient.setGender(Gender.M);
        patient.setContact("0691238621");
        patient.setDateofbirth(new Date());
        patient.setAdress("somewhere");
        patient.setPassword(passwordEncoder.encode("1234"));

        // Manually assign the ID if necessary
        // patient.setId(1L); // Example of manually assigning ID

        patientServices.save(patient);
    }
}
