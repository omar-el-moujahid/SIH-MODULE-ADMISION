package ma.ensa.sihmoduleadmission.service.medicale_history;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.entities.Medical_History;
import ma.ensa.sihmoduleadmission.repos.AppointmentRepo;
import ma.ensa.sihmoduleadmission.repos.Medical_HistoryRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class MedicalHistoryServiceImpl implements Medical_HistoryService {
    private final AppointmentRepo appointmentRepo;
    private Medical_HistoryRepo medicalHistoryRepo;

    public MedicalHistoryServiceImpl(Medical_HistoryRepo medicalHistoryRepo, AppointmentRepo appointmentRepo) {
        this.medicalHistoryRepo = medicalHistoryRepo;
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public Medical_History save(Medical_History medicalHistory) {
        return medicalHistoryRepo.save(medicalHistory);
    }
}
