package ma.ensa.sihmoduleadmission.service.doctor;

import ma.ensa.sihmoduleadmission.dto.DoctorDTO;
import ma.ensa.sihmoduleadmission.entities.Doctor;
import ma.ensa.sihmoduleadmission.expetion.thrabelauthontification;
import org.springframework.data.domain.Page;

public interface DoctorServices {
    Doctor save(Doctor doctor);
    Doctor findbyid(String id);

    DoctorDTO authentication(String CNI, String password) throws thrabelauthontification;

    void Updatepassword(String CIN, String Oldpassword, String Newpassword);

    Page<DoctorDTO> getAllDoctors(int page);

    Page<DoctorDTO> getAllDoctorsBySpeciality(int page, String rolename);

    void delete(String cin);

    void save(DoctorDTO doctorDTO);

    Page<DoctorDTO> findbycin(String cne);
}

