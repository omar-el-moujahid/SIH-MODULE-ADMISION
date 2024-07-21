package ma.ensa.sihmoduleadmission.expetion;

import ma.ensa.sihmoduleadmission.entities.Patient;

public class CINNotFound extends Exception {
    public CINNotFound(String string) {
        super(string);
    }
}
