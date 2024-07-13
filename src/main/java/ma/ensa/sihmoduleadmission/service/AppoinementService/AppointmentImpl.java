package ma.ensa.sihmoduleadmission.service.AppoinementService;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.repos.PatientRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AppointmentImpl implements Appointment {
    private Appointment appointment;

    public AppointmentImpl(Appointment appointment) {
        this.appointment = appointment;
    }
}
