package ma.ensa.sihmoduleadmission.service.planification;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.repos.PlanificationRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class PlanificationServicesImpl implements PlanificationServices {
    private PlanificationRepo planificationRepo ;


    public PlanificationServicesImpl(PlanificationRepo planificationRepo) {
        this.planificationRepo = planificationRepo;
    }
}
