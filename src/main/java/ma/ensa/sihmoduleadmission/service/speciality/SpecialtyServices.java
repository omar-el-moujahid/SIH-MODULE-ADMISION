package ma.ensa.sihmoduleadmission.service.speciality;

import ma.ensa.sihmoduleadmission.dto.SpecialtyDTO;
import ma.ensa.sihmoduleadmission.entities.Specialty;

import java.util.List;

public interface SpecialtyServices {
     Specialty findbyid(Long id);
     List<SpecialtyDTO> findAll();
     Specialty findbyname(String name);
     List<Specialty> findbyname(String name, int page, int size);
     Specialty save(Specialty specialty);

}
