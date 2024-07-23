package ma.ensa.sihmoduleadmission.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AppointmentDTOForDoctor {
    private Long id;
    private Date dateofRDV ;
    private String patientfirstname ;
    private String patientlastname ;
    private String cne ;
}
