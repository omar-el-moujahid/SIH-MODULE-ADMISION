package ma.ensa.sihmoduleadmission.service.planification;

import ma.ensa.sihmoduleadmission.entities.Planification;
import ma.ensa.sihmoduleadmission.entities.Specialty;

import java.util.Date;
import java.util.List;

public interface PlanificationServices {
    Planification save(Planification planification);
    List <Planification> findavalabel(Specialty specialty);

    Planification findPlanificationBySpecialtiesAndAndDate(Specialty specialty, Date date);

    void delete(Planification planification);

    void increment(Long id);

    void desincrement(Long id);
}
