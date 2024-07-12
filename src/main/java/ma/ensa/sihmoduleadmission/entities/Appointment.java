package ma.ensa.sihmoduleadmission.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    private Long id;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Patient patient;
    private Date dateofRDV ;
    @ManyToOne
    private Specialty specialty;
    private Boolean ispasse;
    private Boolean annule;

}
