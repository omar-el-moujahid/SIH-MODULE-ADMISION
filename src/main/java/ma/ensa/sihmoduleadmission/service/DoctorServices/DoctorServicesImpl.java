package ma.ensa.sihmoduleadmission.service.DoctorServices;

import ma.ensa.sihmoduleadmission.repos.DoctorRepo;

public class DoctorServicesImpl implements DoctorServices {
    private DoctorRepo doctorRepo;

    public DoctorServicesImpl(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }
}
