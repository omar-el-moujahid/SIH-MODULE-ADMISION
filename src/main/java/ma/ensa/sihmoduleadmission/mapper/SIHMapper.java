package ma.ensa.sihmoduleadmission.mapper;

import ma.ensa.sihmoduleadmission.dto.*;
import ma.ensa.sihmoduleadmission.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SIHMapper {
    public PatientDTO PatientToDTOPatient(Patient patient){
        PatientDTO patientDTO = new PatientDTO();
        BeanUtils.copyProperties(patient,patientDTO);
        return patientDTO;
    }
    public Patient PatientDTOToPatient(PatientDTO patientDTO){
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDTO,patient);
        return patient;
    }
    public DoctorDTO DoctorToDTODoctor(Doctor doctor){
        DoctorDTO doctorDTO = new DoctorDTO();
        BeanUtils.copyProperties(doctor,doctorDTO);
        doctorDTO.setSpecialtyDTO(doctor.getSpecialty().getSpecialtyName());
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
        appointmentDTO.setDoctorFirstname(appointment.getDoctor().getFirstname());
        appointmentDTO.setDoctorLastname(appointment.getDoctor().getLastname());
        appointmentDTO.setPatientFirstname(appointment.getPatient().getFirstname());
        appointmentDTO.setPatientLastname(appointment.getPatient().getLastname());
        appointmentDTO.setSpecialtyDTO(appointment.getSpecialty().getSpecialtyName());
        return appointmentDTO;
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
}
