package ma.ensa.sihmoduleadmission.entities;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Planification {
    @Id
    private Long id;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;
    private String startAt;
    private String endAt;
    @NotNull
    private int capacity;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Doctor> patients = new ArrayList<>();
}
