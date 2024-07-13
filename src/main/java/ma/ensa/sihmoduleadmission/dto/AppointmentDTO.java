package ma.ensa.sihmoduleadmission.dto;

import lombok.Data;

import java.util.Date;
@Data
public class AppointmentDTO {
    private Long id;
    private String  doctorFullname;
    private String  patientFullname;
    private Date dateofRDV ;
    private SpecialtyDTO specialtyDTO;
    private Boolean ispasse;
    private Boolean annule;

}
