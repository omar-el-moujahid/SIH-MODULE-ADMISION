package ma.ensa.sihmoduleadmission.service.Medical_HistoryService;

import ma.ensa.sihmoduleadmission.repos.Medical_HistoryRepo;

public class MedicalHistoryServiceImpl implements Medical_HistoryService {
    private Medical_HistoryRepo medicalHistoryRepo;

    public MedicalHistoryServiceImpl(Medical_HistoryRepo medicalHistoryRepo) {
        this.medicalHistoryRepo = medicalHistoryRepo;
    }
}
