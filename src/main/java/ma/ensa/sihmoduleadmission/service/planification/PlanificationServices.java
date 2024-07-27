package ma.ensa.sihmoduleadmission.service.planification;

import ma.ensa.sihmoduleadmission.dto.AddPlanificationDTO;
import ma.ensa.sihmoduleadmission.dto.PlanificationDTO;
import ma.ensa.sihmoduleadmission.entities.Planification;
import ma.ensa.sihmoduleadmission.entities.Specialty;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface PlanificationServices {
    Planification save(Planification planification);
    List <Planification> findavalabel(Specialty specialty);

    Planification findPlanificationBySpecialtiesAndAndDate(Specialty specialty, Date date);

    void delete(Planification planification);

    void increment(Long id);

    void desincrement(Long id);

    List<PlanificationDTO> findByDocotorAndDateBetween(String cne, int intel);

    Page<PlanificationDTO> findplanificationByDateBetween(Date startDate, Date endDate, int page);

    Page<PlanificationDTO> findPlanificationBySpecialtiesAndAndDateBetween(Specialty speciality, Date startDate, Date endDate, int page);

    void delete(Long id);

    void addplaning(AddPlanificationDTO dto);
}
