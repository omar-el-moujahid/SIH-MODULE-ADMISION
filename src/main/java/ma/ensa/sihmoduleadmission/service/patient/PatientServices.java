package ma.ensa.sihmoduleadmission.service.patient;

import ma.ensa.sihmoduleadmission.dto.PatientDTO;
import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.expetion.thrabelauthontification;

import java.util.List;

public interface PatientServices {
    Patient save(Patient patient);
    List<PatientDTO> findAll();
    PatientDTO find(String id);
    Patient authentication(String CNI , String password) throws thrabelauthontification;

}
