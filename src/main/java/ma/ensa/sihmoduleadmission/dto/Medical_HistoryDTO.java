package ma.ensa.sihmoduleadmission.dto;
import lombok.Data;

import java.util.Date;
@Data
public class Medical_HistoryDTO {
    private Long id ;
    private Date date;
    private String  doctorFirstname;
    private String  doctorLastname;
    private String  patientFirstname;
    private String  patientLastname;
    private String description;
}
