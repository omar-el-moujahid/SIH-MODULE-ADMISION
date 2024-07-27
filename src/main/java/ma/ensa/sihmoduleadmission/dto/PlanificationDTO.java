package ma.ensa.sihmoduleadmission.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PlanificationDTO {
    private Long id;
    private Date date;
    private String startAt;
    private String endAt;
    private int capacity;
    private int currentcapacity;
    private int TotalePatientAttend;
    private int TotalePatientMissed;
    private String SpecialityName;

}
