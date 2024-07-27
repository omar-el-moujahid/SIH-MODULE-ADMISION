package ma.ensa.sihmoduleadmission.dto;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.sihmoduleadmission.entities.enums.Gender;
import ma.ensa.sihmoduleadmission.entities.securiy.RolesApp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class UsersAppDTO {
    @Id
    private String CNE;
    @NotNull
    @Size(min=8)
    private String password ;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String adress;
    @Size(min = 10 , max = 13)
    private String contact;
    private boolean active;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Temporal(TemporalType.DATE)
    private Date dateofbirth;
    @Email
    private String mail;
    @NotNull
    private List<String> roleApps = new ArrayList<>();
}
