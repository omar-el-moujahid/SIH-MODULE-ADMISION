package ma.ensa.sihmoduleadmission.entities.securiy;

import com.fasterxml.jackson.annotation.JsonProperty;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jdk.jfr.BooleanFlag;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
    @ManyToMany(fetch =FetchType.EAGER)
    private List<RolesApp> roleApps = new ArrayList<>();

    public boolean getActive() {
        return active;
    }
}
