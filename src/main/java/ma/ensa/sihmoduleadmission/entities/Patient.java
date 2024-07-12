package ma.ensa.sihmoduleadmission.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.sihmoduleadmission.entities.enums.Gender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    private String CNE;
    private String firstname;
    private String lastname;
    private String adress;
    private String contact;
    private String mail;
    private Date dateofbirth;
    private Gender gender;
    @OneToMany(mappedBy = "patient")
    Collection<Appointment> appointments = new ArrayList<>();
    @OneToMany(mappedBy = "patient")
    private Collection<Medical_History> medicaleHestories ;
}