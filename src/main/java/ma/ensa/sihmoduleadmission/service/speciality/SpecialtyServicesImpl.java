package ma.ensa.sihmoduleadmission.service.speciality;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.dto.PatientDTO;
import ma.ensa.sihmoduleadmission.dto.SpecialtyDTO;
import ma.ensa.sihmoduleadmission.entities.Specialty;
import ma.ensa.sihmoduleadmission.expetion.ApiRequestExpetion;
import ma.ensa.sihmoduleadmission.mapper.SIHMapper;
import ma.ensa.sihmoduleadmission.repos.SpecialtyRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class SpecialtyServicesImpl implements SpecialtyServices {
    private SpecialtyRepo specialtyRepo ;
    private SIHMapper sihMapper;

    public SpecialtyServicesImpl(SpecialtyRepo specialtyRepo, SIHMapper sihMapper) {
        this.specialtyRepo = specialtyRepo;
        this.sihMapper = sihMapper;
    }


    @Override
    public Specialty findbyid(Long id) {
        return specialtyRepo.findById(id).orElse(null);
    }

    @Override
    public List<SpecialtyDTO> findAll() {
       List<Specialty>  specialties = specialtyRepo.findAll();
        List<SpecialtyDTO> specialtyDTOS = specialties.stream()
                .map(specialty -> sihMapper.SpecialtyToDTOSpecialty(specialty) )
                .collect(Collectors.toList());
        return specialtyDTOS;
    }

    @Override
    public Specialty findbyname(String name) {
        Specialty bySpecialtyName = specialtyRepo.findBySpecialtyName(name);
        if(bySpecialtyName==null) throw new ApiRequestExpetion("No speciality found");
        return bySpecialtyName ;
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
