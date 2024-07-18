package ma.ensa.sihmoduleadmission.dto;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
@Data
public class PatientDTO {
    @Valid

    @NotNull
    @Size(min =8 )
    private String CNE;

    @NotNull
    @Size(min=8 , message = "la taille doit être superieur a 8 ")
    private String password;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private String adress;

    @Size(min = 10, max = 13)
    private String contact;

    @Email
    private String mail;

    @NotNull
    private Date dateofbirth;

    @NotNull
    private String gender;
    @NotNull
    private String Password;
}
