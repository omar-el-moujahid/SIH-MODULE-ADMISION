package ma.ensa.sihmoduleadmission.entities.securiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.sihmoduleadmission.entities.enums.Gender;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class UsersApp {
    @Id
    private String CNE;
    private String firstname;
    private String lastname;
    private String adress;
    private String contact;
    private String mail;
    private Date dateofbirth;
    private Gender gender;
    private  boolean active ;
    @ManyToMany(fetch =FetchType.EAGER)
    private List<RolesApp> roleApps = new ArrayList<>();
}
