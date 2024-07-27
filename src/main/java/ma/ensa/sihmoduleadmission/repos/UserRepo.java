package ma.ensa.sihmoduleadmission.repos;

import ma.ensa.sihmoduleadmission.entities.securiy.RolesApp;
import ma.ensa.sihmoduleadmission.entities.securiy.UsersApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<UsersApp,String> {
    List<UsersApp> findUsersAppsByRoleApps(RolesApp rolesApp);
    Page<UsersApp> findUsersAppsByRoleApps(PageRequest pageRequest, RolesApp rolesApp);
    Page<UsersApp> findUsersAppsByCNE(PageRequest pageRequest,String CNE);
    UsersApp findUsersAppsByCNE(String CNE);
}
