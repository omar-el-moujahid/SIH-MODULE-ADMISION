package ma.ensa.sihmoduleadmission.entities;

import ma.ensa.sihmoduleadmission.entities.enums.Gender;

import java.util.Collection;
import java.util.Date;

public class Patient {
    private String CNE;
    private String firstname;
    private String lastname;
    private String adress;
    private String contact;
    private String mail;
    private Date dateofbirth;
    private Gender gender;
    private Collection<Medicale_Hestory> medicaleHestories ;
}
