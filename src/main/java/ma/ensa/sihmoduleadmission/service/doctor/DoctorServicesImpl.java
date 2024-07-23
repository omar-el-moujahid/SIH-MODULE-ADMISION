package ma.ensa.sihmoduleadmission.service.doctor;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.dto.DoctorDTO;
import ma.ensa.sihmoduleadmission.entities.Doctor;
import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.expetion.ApiRequestExpetion;
import ma.ensa.sihmoduleadmission.expetion.thrabelauthontification;
import ma.ensa.sihmoduleadmission.mapper.SIHMapper;
import ma.ensa.sihmoduleadmission.repos.DoctorRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class DoctorServicesImpl  implements DoctorServices {
    private final PasswordEncoder passwordEncoder;
    private DoctorRepo doctorRepo;
    private SIHMapper sihMapper ;

    public DoctorServicesImpl(DoctorRepo doctorRepo, PasswordEncoder passwordEncoder, SIHMapper sihMapper) {
        this.doctorRepo = doctorRepo;
        this.passwordEncoder = passwordEncoder;
        this.sihMapper = sihMapper;
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepo.save(doctor);
    }

    @Override
    public Doctor findbyid(String id) {
        return doctorRepo.findById(id).orElse(null);
    }
    @Override
    public DoctorDTO authentication(String CNI, String password) throws thrabelauthontification {
        Doctor doctor = doctorRepo.findById(CNI)
                .orElseThrow(() -> new ApiRequestExpetion("CNI or Password are incorrect"));

        if (!passwordEncoder.matches(password, doctor.getPassword())) {
            throw  new ApiRequestExpetion("CNI or Password are incorrect");
        }
        DoctorDTO doctorDTO = sihMapper.DoctorToDTODoctor(doctor);
        return doctorDTO;
    }

    @Override
    public void Updatepassword(String CIN, String Oldpassword, String Newpassword) {
        Doctor doctor = doctorRepo.findById(CIN).orElseThrow(() -> new ApiRequestExpetion("CIN Not Found"));
        if (!passwordEncoder.matches(Oldpassword, doctor.getPassword())) {
            throw new ApiRequestExpetion("Password Not Correct :/");
        }
        if(Newpassword.length()<8){
            throw new ApiRequestExpetion("Password must be at lest 8 caracterse :/");
        }
        doctor.setPassword(passwordEncoder.encode(Newpassword));
        doctorRepo.save(doctor);
    }
}
