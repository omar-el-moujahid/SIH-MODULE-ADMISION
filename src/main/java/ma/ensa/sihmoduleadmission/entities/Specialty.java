package ma.ensa.sihmoduleadmission.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String specialeteName;
    @OneToMany
    private Collection<Doctor> doctors;
    @OneToMany
    private Collection<Appointment> appointments;
}
