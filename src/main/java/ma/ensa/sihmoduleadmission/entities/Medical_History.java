package ma.ensa.sihmoduleadmission.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medical_History {
    @Id
    private Long id ;
    private Date date;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor medcineresponsable;
    private String description;
}
