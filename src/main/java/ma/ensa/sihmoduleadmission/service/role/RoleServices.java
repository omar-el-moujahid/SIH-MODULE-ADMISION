package ma.ensa.sihmoduleadmission.service.role;

import ma.ensa.sihmoduleadmission.dto.PatientDTO;
import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.entities.securiy.RolesApp;
import ma.ensa.sihmoduleadmission.expetion.thrabelauthontification;

import java.util.List;

public interface RoleServices {
   RolesApp findbyname(String s);

}
