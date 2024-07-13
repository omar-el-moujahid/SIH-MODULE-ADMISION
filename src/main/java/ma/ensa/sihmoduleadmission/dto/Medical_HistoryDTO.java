package ma.ensa.sihmoduleadmission.dto;
import lombok.Data;

import java.util.Date;
@Data
public class Medical_HistoryDTO {
    private Long id ;
    private Date date;
    private PatientDTO patientDTO;
    private DoctorDTO medcineresponsable;
    private String description;
}
