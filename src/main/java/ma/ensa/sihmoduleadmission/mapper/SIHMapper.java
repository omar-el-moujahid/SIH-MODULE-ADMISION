package ma.ensa.sihmoduleadmission.mapper;

import ma.ensa.sihmoduleadmission.dto.*;
import ma.ensa.sihmoduleadmission.entities.*;
import ma.ensa.sihmoduleadmission.entities.securiy.RolesApp;
import ma.ensa.sihmoduleadmission.entities.securiy.UsersApp;
import ma.ensa.sihmoduleadmission.repos.Rolerepo;
import ma.ensa.sihmoduleadmission.service.doctor.DoctorServices;
import ma.ensa.sihmoduleadmission.service.patient.PatientServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SIHMapper {
    private Rolerepo rolerepo ;
    public SIHMapper(Rolerepo rolerepo ) {
        this.rolerepo = rolerepo;
    }

    public PatientDTO PatientToDTOPatient(Patient patient){
        PatientDTO patientDTO = new PatientDTO();
        BeanUtils.copyProperties(patient,patientDTO);
        patientDTO.setRole(patient.getRole().getRolename());
        return patientDTO;
    }
    public Patient PatientDTOToPatient(PatientDTO patientDTO){
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDTO,patient);
//        patient.setRole(rolerepo.findByRolename(patientDTO.getRole()));
        return patient;
    }
    public DoctorDTO DoctorToDTODoctor(Doctor doctor){
        DoctorDTO doctorDTO = new DoctorDTO();
        BeanUtils.copyProperties(doctor,doctorDTO);
        doctorDTO.setSpecialtyDTO(doctor.getSpecialty().getSpecialtyName());
        doctorDTO.setRole(doctor.getRole().getRolename());
        return doctorDTO;
    }
    public Doctor DoctorDTOToDoctor(DoctorDTO doctorDTO){
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorDTO,doctor);
        return doctor;
    }
    public AppointmentDTO AppointmentToDTOAppointment(Appointment appointment){
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        BeanUtils.copyProperties(appointment,appointmentDTO);
        appointmentDTO.setSpecialtyDTO(appointment.getSpecialty().getSpecialtyName());
        return appointmentDTO;
    }
    public AppointmentDTOForDoctor AppointmentToAppointmentDTOForDoctor(Appointment appointment){
        AppointmentDTOForDoctor AppointmentDTOForDoctor = new AppointmentDTOForDoctor();
        BeanUtils.copyProperties(appointment,AppointmentDTOForDoctor);
        AppointmentDTOForDoctor.setPatientlastname(appointment.getPatient().getLastname());
        AppointmentDTOForDoctor.setPatientfirstname(appointment.getPatient().getFirstname());
        AppointmentDTOForDoctor.setCne(appointment.getPatient().getCNE());
        return AppointmentDTOForDoctor;
    }
    public Appointment AppointmentDTOToAppointment(AppointmentDTO appointmentDTO){
        Appointment appointment = new Appointment();
        BeanUtils.copyProperties(appointmentDTO,appointment);
        return appointment;
    }

//    public Medical_HistoryDTO AppointmentToDTOAppointment(Medical_History medicalHistory){
//        Medical_HistoryDTO medicalHistoryDTO = new Medical_HistoryDTO();
//        BeanUtils.copyProperties(medicalHistory,medicalHistoryDTO);
//        return medicalHistoryDTO;
//    }
//    public Medical_History AppointmentDTOToAppointment(Medical_HistoryDTO medicalHistoryDTO){
//        Medical_History medicalHistory = new Medical_History();
//        BeanUtils.copyProperties(medicalHistoryDTO,medicalHistory);
//        return medicalHistory;
//    }

    public Medical_HistoryDTO Medical_HistoryToDTOMedical_History(Medical_History medicalHistory) {
        Medical_HistoryDTO medicalHistoryDTO = new Medical_HistoryDTO();
        BeanUtils.copyProperties(medicalHistory, medicalHistoryDTO);
        medicalHistoryDTO.setDoctorFirstname(medicalHistory.getMedcineresponsable().getFirstname());
        medicalHistoryDTO.setDoctorLastname(medicalHistory.getMedcineresponsable().getLastname());
        medicalHistoryDTO.setPatientFirstname(medicalHistory.getPatient().getFirstname());
        medicalHistoryDTO.setPatientLastname(medicalHistory.getPatient().getLastname());
        return medicalHistoryDTO;
    }

    public Medical_History Medical_HistoryDTOToMedical_History(Medical_HistoryDTO medicalHistoryDTO) {
        Medical_History medicalHistory = new Medical_History();
        BeanUtils.copyProperties(medicalHistoryDTO, medicalHistory);
        return medicalHistory;
    }
    public PlanificationDTO PlanificationToDTOPlanification(Planification planification) {
        PlanificationDTO planificationDTO = new PlanificationDTO();
        BeanUtils.copyProperties(planification, planificationDTO);
        planificationDTO.setSpecialityName(planification.getSpecialty().getSpecialtyName());
        return planificationDTO;
    }

    public Planification PlanificationDTOToPlanification(PlanificationDTO planificationDTO) {
        Planification planification = new Planification();
        BeanUtils.copyProperties(planificationDTO, planification);
        return planification;
    }
    public SpecialtyDTO SpecialtyToDTOSpecialty(Specialty specialty) {
        SpecialtyDTO specialtyDTO = new SpecialtyDTO();
        BeanUtils.copyProperties(specialty, specialtyDTO);
        return specialtyDTO;
    }

    public Specialty SpecialtyDTOToSpecialty(SpecialtyDTO specialtyDTO) {
        Specialty specialty = new Specialty();
        BeanUtils.copyProperties(specialtyDTO, specialty);
        return specialty;
    }
    public Medical_History FromMedical_HistoryFromDoctorDTO(Medical_HistoryFromDoctorDTO dto ){
        Medical_History medicalHistory = new Medical_History();
        BeanUtils.copyProperties(dto, medicalHistory);
        return medicalHistory;
    }
    public UsersAppDTO toUsersAppDTO(UsersApp usersApp) {
        UsersAppDTO usersAppDTO = new UsersAppDTO();
        BeanUtils.copyProperties(usersApp, usersAppDTO);
        usersAppDTO.setRoleApps(new ArrayList<>());

        usersApp.getRoleApps().stream()
                .map(RolesApp::getRolename) // Method reference for brevity
                .forEach(usersAppDTO.getRoleApps()::add); // Add each role name to the list

        return usersAppDTO;
    }

    public UsersApp toUsersApp(UsersAppDTO usersAppDTO) {
        UsersApp usersApp = new UsersApp();
        BeanUtils.copyProperties(usersAppDTO, usersApp);

        usersApp.setRoleApps(new ArrayList<>());

        usersAppDTO.getRoleApps().stream()
                .map(roleName -> rolerepo.findByRolename(roleName))
                .forEach(usersApp.getRoleApps()::add); // Add each role entity to the list

        return usersApp;
    }

}
