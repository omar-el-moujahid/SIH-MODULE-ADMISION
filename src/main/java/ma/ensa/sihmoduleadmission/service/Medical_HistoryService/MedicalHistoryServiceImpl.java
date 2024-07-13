package ma.ensa.sihmoduleadmission.service.Medical_HistoryService;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.repos.Medical_HistoryRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class MedicalHistoryServiceImpl implements Medical_HistoryService {
    private Medical_HistoryRepo medicalHistoryRepo;

    public MedicalHistoryServiceImpl(Medical_HistoryRepo medicalHistoryRepo) {
        this.medicalHistoryRepo = medicalHistoryRepo;
    }
}
