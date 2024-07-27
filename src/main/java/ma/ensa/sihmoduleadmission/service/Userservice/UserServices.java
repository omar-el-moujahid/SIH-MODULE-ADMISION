package ma.ensa.sihmoduleadmission.service.Userservice;

import ma.ensa.sihmoduleadmission.dto.UsersAppDTO;
import ma.ensa.sihmoduleadmission.entities.securiy.RolesApp;
import ma.ensa.sihmoduleadmission.entities.securiy.UsersApp;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserServices {

    UsersApp savedUsersApp(UsersAppDTO usersAppDTO);

    UsersApp savedUsersApp(UsersApp usersApp);

    UsersAppDTO findbuid(String usersApp, String password) ;

    void Updatepassword(String cin, String oldpassword, String newpassword);

    void Updateprofil(UsersAppDTO usersAppDTO);

    List<UsersAppDTO> AllUsers();

    List<UsersAppDTO> AllUsersByRole(String rolesApp);

    Page<UsersAppDTO> AllUserspagination(int page);

    Page<UsersAppDTO> AllUsersByRolepaganation(int page, String rolesApp);

    Page<UsersAppDTO> AllUsersByCIN(int page, String cin);

    void AddRoleToUser(String cin, String roleName);

    void RemoveRoleToUser(String cin, String roleName);

    void SuspendeUser(String cin);

    void ActiverUser(String cin);

    void DeleteUser(String cin);

    void AddUser(UsersAppDTO usersAppDTO);
}
