//package ma.ensa.sihmoduleadmission;
//
//import ma.ensa.sihmoduleadmission.dto.PatientDTO;
//import ma.ensa.sihmoduleadmission.entities.*;
//import ma.ensa.sihmoduleadmission.entities.enums.Gender;
//import ma.ensa.sihmoduleadmission.entities.securiy.RolesApp;
//import ma.ensa.sihmoduleadmission.entities.securiy.UsersApp;
//import ma.ensa.sihmoduleadmission.expetion.ApiRequestExpetion;
//import ma.ensa.sihmoduleadmission.mapper.SIHMapper;
//import ma.ensa.sihmoduleadmission.service.Userservice.UserServices;
//import ma.ensa.sihmoduleadmission.service.appointment.AppointmentServiceImpl;
//import ma.ensa.sihmoduleadmission.service.doctor.DoctorServicesImpl;
//import ma.ensa.sihmoduleadmission.service.medicale_history.MedicalHistoryServiceImpl;
//import ma.ensa.sihmoduleadmission.service.patient.PatientServicesImpl;
//import ma.ensa.sihmoduleadmission.service.planification.PlanificationServicesImpl;
//import ma.ensa.sihmoduleadmission.service.role.RoleServicesImpl;
//import ma.ensa.sihmoduleadmission.service.speciality.SpecialtyServicesImpl;
//import org.apache.catalina.mapper.Mapper;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//@Component
//public class DBInit implements ApplicationRunner {
//
//    private final PatientServicesImpl patientServicesImpl;
//    private final DoctorServicesImpl doctorServicesImpl;
//    private final MedicalHistoryServiceImpl medicalHistoryServiceImpl;
//    private final AppointmentServiceImpl appointmentServiceImpl;
//    private final PlanificationServicesImpl planificationServicesImpl;
//    private final SpecialtyServicesImpl specialtyServicesImpl;
//    private final RoleServicesImpl roleServicesImpl;
//    private final PasswordEncoder passwordEncoder;
//    private final SIHMapper sihMapper;
//    private final UserServices userServices;
//
//    public DBInit(DoctorServicesImpl doctorServicesImpl, PatientServicesImpl patientServicesImpl,
//                  MedicalHistoryServiceImpl medicalHistoryServiceImpl, AppointmentServiceImpl appointmentServiceImpl,
//                  PlanificationServicesImpl planificationServicesImpl, SpecialtyServicesImpl specialtyServicesImpl,
//                  RoleServicesImpl roleServicesImpl, PasswordEncoder passwordEncoder, SIHMapper sihMapper, UserServices userServices) {
//        this.doctorServicesImpl = doctorServicesImpl;
//        this.patientServicesImpl = patientServicesImpl;
//        this.medicalHistoryServiceImpl = medicalHistoryServiceImpl;
//        this.appointmentServiceImpl = appointmentServiceImpl;
//        this.planificationServicesImpl = planificationServicesImpl;
//        this.specialtyServicesImpl = specialtyServicesImpl;
//        this.roleServicesImpl = roleServicesImpl;
//        this.passwordEncoder = passwordEncoder;
//        this.sihMapper = sihMapper;
//        this.userServices = userServices;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        // Create and save 7 users with ROLE_ADMIN
//        createAndSaveUsers(7, "ROLE_ADMIN");
//
//        // Create and save 8 users with ROLE_USER
//        createAndSaveUsers(8, "ROLE_USER");
//    }
//
//    private void createAndSaveUsers(int count, String roleName) {
//        for (int i = 1; i <= count; i++) {
//            String email = roleName.toLowerCase() + i + "@example.com";
//            UsersApp usersApp = new UsersApp();
//            usersApp.setActive(true);
//            usersApp.setCNE("CD918057" + i);
//            usersApp.setMail(email);
//            usersApp.setAdress("fd");
//            usersApp.setContact("123456789" + i);
//            usersApp.setFirstname("omar");
//            usersApp.setLastname("omar");
//            usersApp.setPassword(passwordEncoder.encode("12345678")); // Set password with at least 8 characters
//            RolesApp findbyname = roleServicesImpl.findbyname(roleName);
//            usersApp.getRoleApps().add(findbyname);
//            usersApp.setGender(Gender.M); // Assuming all users are male
//            usersApp.setDateofbirth(new Date());
//            userServices.savedUsersApp(usersApp);
//        }
//    }
//}