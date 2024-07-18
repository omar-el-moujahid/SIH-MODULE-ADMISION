package ma.ensa.sihmoduleadmission.service.speciality;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.entities.Specialty;
import ma.ensa.sihmoduleadmission.repos.SpecialtyRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class SpecialtyServicesImpl implements SpecialtyServices {
    private SpecialtyRepo specialtyRepo ;


    public SpecialtyServicesImpl(SpecialtyRepo specialtyRepo) {
        this.specialtyRepo = specialtyRepo;
    }


    @Override
    public Specialty findbyid(Long id) {
        return null;
    }

    @Override
    public List<Specialty> findAll() {
        return List.of();
    }

    @Override
    public List<Specialty> findbyname(String name) {
        return List.of();
    }

    @Override
    public List<Specialty> findbyname(String name, int page, int size) {
        return List.of();
    }

    @Override
    public Specialty save(Specialty specialty) {
        return specialtyRepo.save(specialty) ;
    }
}
