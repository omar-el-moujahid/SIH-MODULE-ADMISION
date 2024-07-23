//package ma.ensa.sihmoduleadmission.services;
//
//import jakarta.annotation.PostConstruct;
//import ma.ensa.sihmoduleadmission.entities.Doctor;
//import ma.ensa.sihmoduleadmission.entities.Planification;
//import ma.ensa.sihmoduleadmission.entities.Specialty;
//import ma.ensa.sihmoduleadmission.entities.enums.Gender;
//import ma.ensa.sihmoduleadmission.entities.securiy.RolesApp;
//
//import ma.ensa.sihmoduleadmission.repos.DoctorRepo;
//import ma.ensa.sihmoduleadmission.repos.PlanificationRepo;
//import ma.ensa.sihmoduleadmission.repos.SpecialtyRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//@Service
//public class DataPopulationService {
//
//    @Autowired
//    private SpecialtyRepo specialtyRepository;
//
//    @Autowired
//    private DoctorRepo doctorRepository;
//
//    @Autowired
//    private PlanificationRepo planificationRepository;
//
//    @PostConstruct
//    public void populateData() {
//        createDoctors();
//        createPlanifications();
//    }
//
//    private void createDoctors() {
//        List<Specialty> specialties = specialtyRepository.findAll();
//
//        for (int i = 0; i < specialties.size(); i++) {
//            Specialty specialty = specialties.get(i);
//            Doctor doctor = Doctor.builder()
//                    .CNE("CD91807" + (i + 1))
//                    .password("12345678")
//                    .firstname("Doctor" + (i + 1))
//                    .lastname("Lastname" + (i + 1))
//                    .adress("Adress" + (i + 1))
//                    .offecNomber("Office" + (i + 1))
//                    .contact("1234567890")
//                    .mail("doctor" + (i + 1) + "@hospital.com")
//                    .dateofbirth(new Date())
//                    .gender(Gender.M)
//                    .specialty(specialty)
//                    .build();
//            doctorRepository.save(doctor);
//        }
//    }
//
//    private void createPlanifications() {
//        List<Doctor> doctors = doctorRepository.findAll();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        for (Doctor doctor : doctors) {
//            for (int i = 0; i < 15; i++) {
//                Calendar calendar = Calendar.getInstance();
//                calendar.add(Calendar.DAY_OF_YEAR, i);
//
//                Planification planification = Planification.builder()
//                        .date(calendar.getTime())
//                        .startAt("09:00")
//                        .endAt("17:00")
//                        .capacity(10)
//                        .currentcapacity(0)
//                        .doctors(List.of(doctor))
//                        .specialties(List.of(doctor.getSpecialty()))
//                        .build();
//                planificationRepository.save(planification);
//            }
//        }
//    }
//}
