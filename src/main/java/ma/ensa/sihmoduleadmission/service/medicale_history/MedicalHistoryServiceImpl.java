package ma.ensa.sihmoduleadmission.service.medicale_history;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.entities.Medical_History;
import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.repos.AppointmentRepo;
import ma.ensa.sihmoduleadmission.repos.Medical_HistoryRepo;
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
    private PatientServicesImpl patientServicesImp;

    public MedicalHistoryServiceImpl(Medical_HistoryRepo medicalHistoryRepo, AppointmentRepo appointmentRepo, PatientServicesImpl patientServices) {
        this.medicalHistoryRepo = medicalHistoryRepo;
        this.appointmentRepo = appointmentRepo;
        this.patientServicesImp = patientServices;
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
}
