package ma.ensa.sihmoduleadmission.dto;

import lombok.Data;

import java.util.Date;
@Data
public class AppointmentDTO {
    private Long id;
    private String  doctorFirstname;
    private String  doctorLastname;
    private String  patientFirstname;
    private String  patientLastname;
    private Date dateofRDV ;
    private String specialtyDTO;
    private Boolean ispasse;
    private Boolean annule;

}
