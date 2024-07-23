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
//        createPlanifications();
//    }
//    private void createPlanifications() {
//        List<Doctor> doctors = doctorRepository.findAll();
//        List<Specialty> specialties = specialtyRepository.findAll();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        Calendar calendar = Calendar.getInstance();
//        // Iterate over each specialty
//        for (Specialty specialty : specialties) {
//                calendar.add(Calendar.DAY_OF_YEAR, 0); // Move to the next day
//                 Planification planification = Planification.builder()
//                            .date(calendar.getTime())
//                            .startAt("09:00")
//                            .endAt("17:00")
//                            .capacity(10)
//                            .currentcapacity(0)
//                            .doctors(doctors) // Associate all doctors
//                            .specialty(specialty) // Assign the current specialty
//                            .build();
//                    planificationRepository.save(planification);
//
//        }
//    }
//
//}
