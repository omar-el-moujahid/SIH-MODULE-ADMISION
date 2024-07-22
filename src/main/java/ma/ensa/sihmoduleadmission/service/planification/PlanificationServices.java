package ma.ensa.sihmoduleadmission.service.planification;

import ma.ensa.sihmoduleadmission.entities.Planification;
import ma.ensa.sihmoduleadmission.entities.Specialty;

import java.util.List;

public interface PlanificationServices {
    Planification save(Planification planification);
    List <Planification> findavalabel(Specialty specialty);
}
