package ma.ensa.sihmoduleadmission.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.sihmoduleadmission.entities.securiy.RolesApp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Planification {
    @Id
    private Long id;
    private Date date;
    private String startAt;
    private String endat;
    private int capacitt;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Patient> patients = new ArrayList<>();

}
