package ma.ensa.sihmoduleadmission.service.PatientServices;

import ma.ensa.sihmoduleadmission.repos.DoctorRepo;
import ma.ensa.sihmoduleadmission.repos.PatientRepo;

public class PatientServicesImpl implements PatientServices {
    private PatientRepo patientRepo;


    public PatientServicesImpl(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }
}
