package ma.ensa.sihmoduleadmission.expetion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExptionHandel {
//    @ExceptionHandler(value = {ApiRequestExpetion.class})
//    public ResponseEntity<Object> ApiExptionHandel(ApiRequestExpetion e){
//        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
//        ApiExption apiExption = new ApiExption(e.getMessage(),
//                e,
//                badRequest,
//                ZonedDateTime.now(ZoneId.of("Africa/Casablanca")));
//        return new ResponseEntity<>(apiExption , badRequest);
//    }

    @ExceptionHandler(value = {ApiRequestExpetion.class})
    public ResponseEntity<Object> handleApiException(ApiRequestExpetion e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        Map<String, Object> response = new HashMap<>();
        response.put("type", "custom");
        response.put("message", e.getMessage());
        response.put("timestamp", ZonedDateTime.now(ZoneId.of("Africa/Casablanca")));

        return new ResponseEntity<>(response, badRequest);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("type", "validation");
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("timestamp", ZonedDateTime.now(ZoneId.of("Africa/Casablanca")));

        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            validationErrors.put(error.getField(), error.getDefaultMessage());
        }
        response.put("errors", validationErrors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
