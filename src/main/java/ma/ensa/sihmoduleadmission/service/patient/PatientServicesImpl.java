package ma.ensa.sihmoduleadmission.service.patient;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.dto.PatientDTO;
import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.entities.securiy.RolesApp;
import ma.ensa.sihmoduleadmission.expetion.ApiRequestExpetion;
import ma.ensa.sihmoduleadmission.expetion.CINNotFound;
import ma.ensa.sihmoduleadmission.expetion.thrabelauthontification;
import ma.ensa.sihmoduleadmission.mapper.SIHMapper;
import ma.ensa.sihmoduleadmission.repos.PatientRepo;
import ma.ensa.sihmoduleadmission.repos.Rolerepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class PatientServicesImpl implements PatientServices {
    private PatientRepo patientRepo;
    private Rolerepo rolerepo;
    private SIHMapper sihMapper;
    private PasswordEncoder passwordEncoder;
    public PatientServicesImpl(PatientRepo patientRepo, Rolerepo rolerepo, SIHMapper sihMapper, PasswordEncoder passwordEncoder) {
        this.patientRepo = patientRepo;
        this.rolerepo = rolerepo;
        this.sihMapper = sihMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Patient save(Patient patient) {
        Patient patient1= patientRepo.findById(patient.getCNE()).orElse(null);
        if(patient1!=null) throw new ApiRequestExpetion("CIN already used");
        RolesApp defaultRole = rolerepo.findByRolename("ROLE_PATIENT");
        patient.setRole(defaultRole);
        return patientRepo.save(patient);
    }

    @Override
    public List<PatientDTO> findAll() {
        List<Patient> patients = patientRepo.findAll();
        List<PatientDTO> patientDTOS = patients.stream()
                .map(patient -> sihMapper.PatientToDTOPatient(patient) )
                .collect(Collectors.toList());
        return patientDTOS;
    }

    @Override
    public PatientDTO find(String id) {
        Patient patient=patientRepo.findById(id).orElse(null);
        PatientDTO patientDTO =sihMapper.PatientToDTOPatient(patient);
        patientDTO.setRole(patient.getRole().getRolename());
        return patientDTO;
    }

    @Override
    public Patient findbyid(String id) {
        return patientRepo.findById(id).orElseThrow( () -> new ApiRequestExpetion("CNI Not existe"));
    }

    @Override
    public Patient authentication(String CNI, String password) throws thrabelauthontification {
        Patient patient = patientRepo.findById(CNI)
                .orElseThrow(() -> new ApiRequestExpetion("CNI or Password are incorrect"));

        if (!passwordEncoder.matches(password, patient.getPassword())) {
            throw  new ApiRequestExpetion("CNI or Password are incorrect");
        }
        return patient;
    }

    @Override
    public void Updatepassword(String CIN, String Oldpassword, String Newpassword) {
        Patient patient = patientRepo.findById(CIN).orElseThrow(() -> new ApiRequestExpetion("CIN Not Found"));
        if (!passwordEncoder.matches(Oldpassword, patient.getPassword())) {
            throw new ApiRequestExpetion("Password Not Correct :/");
        }
        if(Newpassword.length()<8){
            throw new ApiRequestExpetion("Password must be at lest 8 caracterse :/");
        }
        patientRepo.updateByCNEPassword(CIN,passwordEncoder.encode(Newpassword));
    }
}
