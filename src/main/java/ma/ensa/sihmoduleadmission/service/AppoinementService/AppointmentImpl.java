package ma.ensa.sihmoduleadmission.service.AppoinementService;

import ma.ensa.sihmoduleadmission.repos.PatientRepo;

public class AppointmentImpl implements Appointment {
    private Appointment appointment;

    public AppointmentImpl(Appointment appointment) {
        this.appointment = appointment;
    }
}
