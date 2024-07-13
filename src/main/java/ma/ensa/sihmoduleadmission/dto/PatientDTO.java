package ma.ensa.sihmoduleadmission.dto;

import lombok.Data;
import ma.ensa.sihmoduleadmission.entities.enums.Gender;

import java.util.Date;
@Data
public class PatientDTO {
    private String CNE;
    private String firstname;
    private String lastname;
    private String adress;
    private String contact;
    private String mail;
    private Date dateofbirth;
    private Gender gender;
}
