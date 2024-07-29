package ma.ensa.sihmoduleadmission.service.patient;

import ma.ensa.sihmoduleadmission.dto.PatientDTO;
import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.expetion.thrabelauthontification;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PatientServices {
    Patient save(Patient patient);

    List<PatientDTO> findAll();

    PatientDTO find(String id);
    Patient findbyid(String id);

    Patient authentication(String CNI, String password) throws thrabelauthontification;

    void Updatepassword(String CIN, String Oldpassword, String Newpassword);

    long count();
}