package ma.ensa.sihmoduleadmission.service.medicale_history;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.dto.Medical_HistoryFromDoctorDTO;
import ma.ensa.sihmoduleadmission.entities.Doctor;
import ma.ensa.sihmoduleadmission.entities.Medical_History;
import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.expetion.ApiRequestExpetion;
import ma.ensa.sihmoduleadmission.mapper.SIHMapper;
import ma.ensa.sihmoduleadmission.repos.AppointmentRepo;
import ma.ensa.sihmoduleadmission.repos.Medical_HistoryRepo;
import ma.ensa.sihmoduleadmission.service.doctor.DoctorServices;
import ma.ensa.sihmoduleadmission.service.patient.PatientServices;
import ma.ensa.sihmoduleadmission.service.patient.PatientServicesImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class MedicalHistoryServiceImpl implements Medical_HistoryService {
    private final AppointmentRepo appointmentRepo;
    private Medical_HistoryRepo medicalHistoryRepo;
    private PatientServices patientServicesImp;
    private DoctorServices doctorServices;
    private SIHMapper sihMapper;
    public MedicalHistoryServiceImpl(Medical_HistoryRepo medicalHistoryRepo, AppointmentRepo appointmentRepo, PatientServicesImpl patientServices, DoctorServices doctorServices, SIHMapper sihMapper) {
        this.medicalHistoryRepo = medicalHistoryRepo;
        this.appointmentRepo = appointmentRepo;
        this.patientServicesImp = patientServices;
        this.doctorServices = doctorServices;
        this.sihMapper = sihMapper;
    }

    @Override
    public Medical_History save(Medical_History medicalHistory) {
        return medicalHistoryRepo.save(medicalHistory);
    }

    @Override
    public List<Medical_History> findAll() {
        return medicalHistoryRepo.findAll();
    }
    @Override
    public List<Medical_History> findbypatientid(String id) {
        Patient patient = patientServicesImp.findbyid(id);
        return medicalHistoryRepo.findByPatient( patient);
    }

    public Medical_History add(Medical_HistoryFromDoctorDTO medicalHistory) {
        Medical_History medicalHistory1 = sihMapper.FromMedical_HistoryFromDoctorDTO(medicalHistory);
        Doctor doctor = doctorServices.findbyid(medicalHistory.getDoctorcne());
        if(doctor==null) throw  new ApiRequestExpetion("Doctor not found");
        Patient patient = patientServicesImp.findbyid(medicalHistory.getPatientcne());
        if(patient==null) throw  new ApiRequestExpetion("Patient not found");
        medicalHistory1.setPatient(patient);
        medicalHistory1.setMedcineresponsable(doctor);
        return medicalHistoryRepo.save(medicalHistory1)  ;
    }
}
