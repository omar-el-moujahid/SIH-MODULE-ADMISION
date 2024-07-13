package ma.ensa.sihmoduleadmission.service.PlanificationService;

import ma.ensa.sihmoduleadmission.repos.PatientRepo;
import ma.ensa.sihmoduleadmission.repos.PlanificationRepo;

public class PlanificationServicesImpl implements PlanificationServices {
    private PlanificationRepo planificationRepo ;


    public PlanificationServicesImpl(PlanificationRepo planificationRepo) {
        this.planificationRepo = planificationRepo;
    }
}
