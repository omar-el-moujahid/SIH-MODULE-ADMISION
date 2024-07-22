package ma.ensa.sihmoduleadmission.service.medicale_history;

import ma.ensa.sihmoduleadmission.entities.Medical_History;
import ma.ensa.sihmoduleadmission.entities.Patient;

import java.util.List;

public interface Medical_HistoryService {
    Medical_History save(Medical_History medicalHistory);

    List<Medical_History> findAll();

    List<Medical_History> findbypatientid(String id);
}