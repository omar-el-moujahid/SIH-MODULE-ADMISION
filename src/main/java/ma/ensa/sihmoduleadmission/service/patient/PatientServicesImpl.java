package ma.ensa.sihmoduleadmission.service.patient;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.dto.PatientDTO;
import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.expetion.ApiRequestExpetion;
import ma.ensa.sihmoduleadmission.expetion.thrabelauthontification;
import ma.ensa.sihmoduleadmission.mapper.SIHMapper;
import ma.ensa.sihmoduleadmission.repos.PatientRepo;
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
    private SIHMapper sihMapper;
    private PasswordEncoder passwordEncoder;
    public PatientServicesImpl(PatientRepo patientRepo, SIHMapper sihMapper, PasswordEncoder passwordEncoder) {
        this.patientRepo = patientRepo;
        this.sihMapper = sihMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Patient save(Patient patient) {
        Patient patient1= patientRepo.findById(patient.getCNE()).orElse(null);
        if(patient1!=null) throw new ApiRequestExpetion("CIN already used");
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
        return patientDTO;
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
}
