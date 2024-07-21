package ma.ensa.sihmoduleadmission.service.planification;

import ma.ensa.sihmoduleadmission.entities.Planification;
import ma.ensa.sihmoduleadmission.entities.Specialty;

import java.util.List;

public interface PlanificationServices {
    Planification save(Planification planification);
    Planification findAvailable(Specialty specialty);
}
