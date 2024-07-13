package ma.ensa.sihmoduleadmission.service.DoctorServices;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.repos.DoctorRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class DoctorServicesImpl implements DoctorServices {
    private DoctorRepo doctorRepo;

    public DoctorServicesImpl(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }
}
