package ma.ensa.sihmoduleadmission.service.planification;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.entities.Planification;
import ma.ensa.sihmoduleadmission.entities.Specialty;
import ma.ensa.sihmoduleadmission.expetion.ApiRequestExpetion;
import ma.ensa.sihmoduleadmission.repos.PlanificationRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class PlanificationServicesImpl implements PlanificationServices {
    private PlanificationRepo planificationRepo ;


    public PlanificationServicesImpl(PlanificationRepo planificationRepo) {
        this.planificationRepo = planificationRepo;
    }

    @Override
    public Planification save(Planification planification) {
        return planificationRepo.save(planification);
    }

    @Override
    public List <Planification> findavalabel(Specialty specialty) {
        List <Planification> planifications = planificationRepo.findAvailablePlanifications(specialty.getId(),new Date());
        if(planifications==null){
            throw new ApiRequestExpetion("No Appointement are avalabel for this speciality");
        }
        return planifications;
    }
}
