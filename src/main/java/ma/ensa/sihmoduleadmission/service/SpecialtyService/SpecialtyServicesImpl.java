package ma.ensa.sihmoduleadmission.service.SpecialtyService;

import ma.ensa.sihmoduleadmission.repos.PlanificationRepo;
import ma.ensa.sihmoduleadmission.repos.SpecialtyRepo;

public class SpecialtyServicesImpl implements SpecialtyServices {
    private SpecialtyRepo specialtyRepo ;


    public SpecialtyServicesImpl(SpecialtyRepo specialtyRepo) {
        this.specialtyRepo = specialtyRepo;
    }
}
