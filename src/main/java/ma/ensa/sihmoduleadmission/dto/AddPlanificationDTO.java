package ma.ensa.sihmoduleadmission.dto;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class AddPlanificationDTO {
    @NotNull
    private Date date;
    @NotNull
    private String startAt;
    @NotNull
    private String endAt;
    @NotNull
    @Min(value = 1, message = "Capacity must be greater than zero")
    private int capacity;
    private String specialityName; // Make sure this is correct
}