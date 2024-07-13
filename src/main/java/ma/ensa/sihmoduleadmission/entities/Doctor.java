package ma.ensa.sihmoduleadmission.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.sihmoduleadmission.entities.enums.Gender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Doctor {
    @Id
    private String CNE;
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
    private Gender gender;
    @OneToMany(mappedBy = "medcine")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
    Collection<Appointment> appointments = new ArrayList<>();
    @ManyToOne
    private Specialty specialty;
}
