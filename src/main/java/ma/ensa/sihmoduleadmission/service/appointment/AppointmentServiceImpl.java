package ma.ensa.sihmoduleadmission.service.appointment;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.repos.AppointmentRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentRepo appointmentRepo;
    public AppointmentServiceImpl(AppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

}
