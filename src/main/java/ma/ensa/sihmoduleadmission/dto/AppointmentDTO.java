package ma.ensa.sihmoduleadmission.dto;

import lombok.Data;

import java.util.Date;
@Data
public class AppointmentDTO {
    private Long id;
    private Date dateofRDV ;
    private String specialtyDTO;

}
