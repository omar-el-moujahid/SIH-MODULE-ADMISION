package ma.ensa.sihmoduleadmission.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @NotNull
    @Column(unique = true)
    private String specialeteName;
    @OneToMany
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
    private Collection<Doctor> doctors;
    @OneToMany
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
    private Collection<Appointment> appointments;
}
