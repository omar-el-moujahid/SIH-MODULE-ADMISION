package ma.ensa.sihmoduleadmission.web;

import jakarta.validation.Valid;
import ma.ensa.sihmoduleadmission.dto.UsersAppDTO;
import ma.ensa.sihmoduleadmission.service.Userservice.UserServices;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Validated
public class UserControler {
    private UserServices userServices;

    public UserControler(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/user/save")
    public void save(@Valid @RequestBody UsersAppDTO usersApp){
        userServices.savedUsersApp(usersApp);
    }
    @PostMapping("/user/Updatepassword")
    public ResponseEntity<String> updatePassword(@RequestParam String CIN, @RequestParam String Oldpassword, @RequestParam String Newpassword) {
        userServices.Updatepassword(CIN, Oldpassword, Newpassword);
        return ResponseEntity.ok("Password updated successfully");
    }
    @PostMapping("/user/Updateprofil")
    public ResponseEntity<String> Updateprofil( @RequestBody UsersAppDTO usersAppDTO) {
        userServices.Updateprofil(usersAppDTO);
        return ResponseEntity.ok("Password updated successfully");
    }
    @GetMapping("/user/login")
    public UsersAppDTO save(@RequestParam String CNI, @RequestParam String password){
        return userServices.findbuid(CNI,password);
    }
    @GetMapping("/user/AllUsers")
    public List<UsersAppDTO> AllUsers(){
        return userServices.AllUsers();
    }
    @GetMapping("/user/AllUsers/{page}")
    public Page<UsersAppDTO> AllUsers(@PathVariable(name = "page") int page){
        return userServices.AllUserspagination(page);
    }
    @GetMapping("/user/AllUsersByRole")
    public List<UsersAppDTO> AllUsersByRole(@RequestParam String rolesApp){
             return userServices.AllUsersByRole(rolesApp);
    }
    @GetMapping("/user/AllUsersByRole/pagination")
    public Page<UsersAppDTO> AllUsersByRolepaganation( @RequestParam int page,@RequestParam String rolesApp){
             return userServices.AllUsersByRolepaganation(page, rolesApp);
    }
    @GetMapping("/user/AllUsersByCIN")
    public Page<UsersAppDTO> AllUsersByCIN( @RequestParam int page,@RequestParam String CIN){
             return userServices.AllUsersByCIN(page, CIN);
    }
    @PostMapping("/user/AddRoleToUser")
    public String AddRoleToUser( @RequestParam String cin,@RequestParam String RoleName){
              userServices.AddRoleToUser(cin, RoleName);
              return "Add Successfully";
    }
    @PostMapping("/user/RemoveRole")
    public String RemoveRole( @RequestParam String cin,@RequestParam String RoleName){
              userServices.RemoveRoleToUser(cin, RoleName);
              return "Remove  Successfully";
    }
    @PostMapping("/user/Suspender")
    public String Suspender( @RequestParam String cin){
              userServices.SuspendeUser(cin);
              return "Suspender  Successfully";
    }
    @PostMapping("/user/Activer")
    public String Activer( @RequestParam String cin){
              userServices.ActiverUser(cin);
              return "Active  Successfully";
    }
    @DeleteMapping("/user/Delete")
    public String Delete( @RequestParam String cin){
              userServices.DeleteUser(cin);
              return "Delete  Successfully";
    }
    @PostMapping("/user/AddUser")
    public void AddUser( @Valid @RequestBody UsersAppDTO usersAppDTO){
        userServices.AddUser(usersAppDTO);
    }
    @GetMapping("/user/Count")
    public Long countUser() {
        return userServices.count();
    }
}
