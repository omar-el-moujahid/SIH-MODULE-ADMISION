package ma.ensa.sihmoduleadmission.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.sihmoduleadmission.entities.enums.Gender;
import ma.ensa.sihmoduleadmission.entities.securiy.RolesApp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {
    @Id
    @Size(min =8 , max=9)
    private String CNE;
    @NotNull
    @Size(min=8)
    private String password;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String adress;
    private String offecNomber;
    @NotNull
    private String contact;
    @NotNull
    private String mail;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateofbirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToMany(mappedBy = "doctor")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
    Collection<Appointment> appointments = new ArrayList<>();
    @ManyToOne
    private Specialty specialty;
    @ManyToOne
    private RolesApp role;
}
