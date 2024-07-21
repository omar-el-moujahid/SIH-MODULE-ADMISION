package ma.ensa.sihmoduleadmission.entities;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medical_History {
    @Id     @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id ;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor medcineresponsable;
    private String description;
}
