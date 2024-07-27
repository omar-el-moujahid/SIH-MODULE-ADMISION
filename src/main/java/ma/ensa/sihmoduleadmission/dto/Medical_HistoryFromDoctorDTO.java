package ma.ensa.sihmoduleadmission.dto;
import lombok.Data;

import java.util.Date;

@Data
public class Medical_HistoryFromDoctorDTO {
    private Date date;
    private String doctorcne;
    private String patientcne;
    private String description;
    private String thedisease;
    private String Medicine;
    private String Notes;
}
