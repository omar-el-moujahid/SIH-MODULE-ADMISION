package ma.ensa.sihmoduleadmission.expetion;

import lombok.Data;
import org.json.HTTP;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.TimeZone;
@Data
public class ApiExption {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime zonedDateTime;

    public ApiExption(String message, Throwable throwable, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }

}
