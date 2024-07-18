package ma.ensa.sihmoduleadmission.expetion;

public class ApiRequestExpetion extends RuntimeException{
    public ApiRequestExpetion(String message) {
        super(message);
    }

    public ApiRequestExpetion(String message, Throwable cause) {
        super(message, cause);
    }
}
