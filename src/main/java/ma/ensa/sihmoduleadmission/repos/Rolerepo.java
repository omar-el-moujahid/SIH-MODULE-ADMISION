package ma.ensa.sihmoduleadmission.repos;

import ma.ensa.sihmoduleadmission.entities.securiy.RolesApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Rolerepo extends JpaRepository<RolesApp,Long> {
    RolesApp findByRolename(String Rolename);
}
