package ma.ensa.sihmoduleadmission.entities;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    private Long id;
    @NotNull
    @ManyToOne
    private Doctor doctor;
    @NotNull
    @ManyToOne
    private Patient patient;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dateofRDV ;
    @NotNull
    @ManyToOne
    private Specialty specialty;
    private Boolean ispasse;
    private Boolean annule;

}
