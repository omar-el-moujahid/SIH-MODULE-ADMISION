package ma.ensa.sihmoduleadmission.service.PatientServices;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.repos.DoctorRepo;
import ma.ensa.sihmoduleadmission.repos.PatientRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class PatientServicesImpl implements PatientServices {
    private PatientRepo patientRepo;


    public PatientServicesImpl(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }
}
