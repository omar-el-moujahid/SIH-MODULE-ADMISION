package ma.ensa.sihmoduleadmission.service.doctor;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.dto.DoctorDTO;
import ma.ensa.sihmoduleadmission.entities.Doctor;
import ma.ensa.sihmoduleadmission.entities.Specialty;
import ma.ensa.sihmoduleadmission.entities.securiy.RolesApp;
import ma.ensa.sihmoduleadmission.expetion.ApiRequestExpetion;
import ma.ensa.sihmoduleadmission.expetion.thrabelauthontification;
import ma.ensa.sihmoduleadmission.mapper.SIHMapper;
import ma.ensa.sihmoduleadmission.repos.DoctorRepo;
import ma.ensa.sihmoduleadmission.repos.Rolerepo;
import ma.ensa.sihmoduleadmission.repos.SpecialtyRepo;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class DoctorServicesImpl  implements DoctorServices {
    private final PasswordEncoder passwordEncoder;
    private final SpecialtyRepo specialtyRepo;
    private final Rolerepo rolerepo;
    private DoctorRepo doctorRepo;
    private SIHMapper sihMapper ;

    public DoctorServicesImpl(DoctorRepo doctorRepo, PasswordEncoder passwordEncoder, @Lazy SIHMapper sihMapper, SpecialtyRepo specialtyRepo, Rolerepo rolerepo) {
        this.doctorRepo = doctorRepo;
        this.passwordEncoder = passwordEncoder;
        this.sihMapper = sihMapper;
        this.specialtyRepo = specialtyRepo;
        this.rolerepo = rolerepo;
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

    @Override
    public Page<DoctorDTO> getAllDoctors(int page) {
        Page<Doctor> all = doctorRepo.findAll(PageRequest.of(page, 10));
        Page<DoctorDTO> map1 = all.map(doctor -> sihMapper.DoctorToDTODoctor(doctor));
        return map1 ;
    }
    @Override
    public Page<DoctorDTO> getAllDoctorsBySpeciality(int page, String SpecialityName) {
        Specialty bySpecialtyName = specialtyRepo.findBySpecialtyName(SpecialityName);
        Page<Doctor> all = doctorRepo.findDoctorBySpecialty(PageRequest.of(page, 10),bySpecialtyName);
        Page<DoctorDTO> map1 = all.map(doctor -> sihMapper.DoctorToDTODoctor(doctor));
        return map1 ;
    }
    @Override
    public void delete(String cin) {
        Doctor doctor = doctorRepo.findById(cin).orElseThrow(() -> new ApiRequestExpetion("Doctor not found"));
        doctorRepo.delete(doctor);
    }
    @Override
    public void save(DoctorDTO doctorDTO) {
        if (doctorRepo.existsById(doctorDTO.getCNE())) {
            throw new ApiRequestExpetion("Doctor with CNE already exists");
        }        Doctor doctor = sihMapper.DoctorDTOToDoctor(doctorDTO);
        Specialty specialty = specialtyRepo.findBySpecialtyName(doctorDTO.getSpecialtyDTO());
        if (specialty == null) {
            throw new ApiRequestExpetion("Specialty not found");
        }
        doctor.setSpecialty(specialty);
        RolesApp byRolename = rolerepo.findByRolename("ROLE_DOCTOR");
        doctor.setRole(byRolename);
        doctor.setPassword(passwordEncoder.encode(doctorDTO.getPassword()));
        doctorRepo.save(doctor);
    }
    @Override
    public Page<DoctorDTO> findbycin(String cne) {
        Page<Doctor> doctorByCNE = doctorRepo.findDoctorByCNE(PageRequest.of(0, 1), cne);
        Page<DoctorDTO> map = doctorByCNE.map(doctor -> sihMapper.DoctorToDTODoctor(doctor));
        return map;
    }
}
