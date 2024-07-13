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
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    private String CNE;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String adress;
    private String contact;
    private String mail;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateofbirth;
    private Gender gender;
    @OneToMany(mappedBy = "patient")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
    Collection<Appointment> appointments = new ArrayList<>();
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
    @OneToMany(mappedBy = "patient")
    private Collection<Medical_History> medicaleHestories ;
}
