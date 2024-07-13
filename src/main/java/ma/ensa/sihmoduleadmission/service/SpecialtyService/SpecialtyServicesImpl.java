package ma.ensa.sihmoduleadmission.service.SpecialtyService;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.repos.PlanificationRepo;
import ma.ensa.sihmoduleadmission.repos.SpecialtyRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class SpecialtyServicesImpl implements SpecialtyServices {
    private SpecialtyRepo specialtyRepo ;


    public SpecialtyServicesImpl(SpecialtyRepo specialtyRepo) {
        this.specialtyRepo = specialtyRepo;
    }
}
