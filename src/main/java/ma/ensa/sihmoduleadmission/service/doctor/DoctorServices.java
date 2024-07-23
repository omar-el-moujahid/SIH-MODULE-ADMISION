package ma.ensa.sihmoduleadmission.service.doctor;

import ma.ensa.sihmoduleadmission.dto.DoctorDTO;
import ma.ensa.sihmoduleadmission.entities.Doctor;
import ma.ensa.sihmoduleadmission.expetion.thrabelauthontification;

public interface DoctorServices {
    Doctor save(Doctor doctor);
    Doctor findbyid(String id);

    DoctorDTO authentication(String CNI, String password) throws thrabelauthontification;

    void Updatepassword(String CIN, String Oldpassword, String Newpassword);
}
