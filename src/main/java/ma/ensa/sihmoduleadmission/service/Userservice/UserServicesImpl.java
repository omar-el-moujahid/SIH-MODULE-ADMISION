package ma.ensa.sihmoduleadmission.service.Userservice;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.dto.UsersAppDTO;
import ma.ensa.sihmoduleadmission.entities.securiy.RolesApp;
import ma.ensa.sihmoduleadmission.entities.securiy.UsersApp;
import ma.ensa.sihmoduleadmission.expetion.ApiRequestExpetion;
import ma.ensa.sihmoduleadmission.mapper.SIHMapper;
import ma.ensa.sihmoduleadmission.repos.Rolerepo;
import ma.ensa.sihmoduleadmission.repos.UserRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserServicesImpl implements UserServices  {
    private final Rolerepo rolerepo;
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private SIHMapper sihMapper;

    public UserServicesImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, SIHMapper sihMapper, Rolerepo rolerepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.sihMapper = sihMapper;
        this.rolerepo = rolerepo;
    }


    @Override
    public UsersApp savedUsersApp(UsersAppDTO usersAppDTO) {
        UsersApp usersApp1 = userRepo.findById(usersAppDTO.getCNE()).orElse(null);
        if(usersApp1!=null) throw new ApiRequestExpetion("User with this CIN already existe");
        usersAppDTO.setPassword(passwordEncoder.encode(usersAppDTO.getPassword()));
        UsersApp usersApp = sihMapper.toUsersApp(usersAppDTO);
        return userRepo.save(usersApp);
    }
    @Override
    public UsersApp savedUsersApp(UsersApp usersApp) {
        return userRepo.save(usersApp);
    }

    @Override
    public UsersAppDTO findbuid(String cne, String password) {
        UsersApp usersApp = userRepo.findById(cne).orElseThrow(() -> new ApiRequestExpetion("User or Password incorrect"));
        if (!passwordEncoder.matches(password, usersApp.getPassword())) {
            throw new ApiRequestExpetion("User or Password incorrect");
        }
        if (!usersApp.getActive()) {
            throw new ApiRequestExpetion("You are not allowed");
        }
        UsersAppDTO usersAppDTO=sihMapper.toUsersAppDTO(usersApp);
        return usersAppDTO;
    }

    @Override
    public void Updatepassword(String cin, String oldpassword, String newpassword) {
        UsersApp usersApp = userRepo.findById(cin).orElseThrow(() -> new ApiRequestExpetion("CIN Not Found"));
        if (!passwordEncoder.matches(oldpassword, usersApp.getPassword())) {
            throw new ApiRequestExpetion("Password Not Correct :/");
        }
        if(newpassword.length()<8){
            throw new ApiRequestExpetion("Password must be at lest 8 caracterse :/");
        }
        usersApp.setPassword(passwordEncoder.encode(newpassword));
        userRepo.save(usersApp);
    }

    @Override
    public void Updateprofil(UsersAppDTO usersAppDTO) {
        UsersApp usersApp = new UsersApp();
        UsersApp usersApp1 = sihMapper.toUsersApp(usersAppDTO);
        userRepo.save(usersApp1);
    }

    @Override
    public List<UsersAppDTO> AllUsers() {
        List<UsersApp> all = userRepo.findAll();
        List<UsersAppDTO> usersAppDTOS = all.stream().map(usersApp -> sihMapper.toUsersAppDTO(usersApp)).collect(Collectors.toList());
        return usersAppDTOS;
    }

    @Override
    public List<UsersAppDTO> AllUsersByRole(String rolesApp) {
        RolesApp byRolename = rolerepo.findByRolename(rolesApp);
        if(byRolename==null) throw new ApiRequestExpetion("Role not found");
        List<UsersApp> usersAppsByRoleApps = userRepo.findUsersAppsByRoleApps(byRolename);
        List<UsersAppDTO> usersAppDTOS = usersAppsByRoleApps.stream().map(usersApp -> sihMapper.toUsersAppDTO(usersApp)).collect(Collectors.toList());
        return  usersAppDTOS ;
    }

    @Override
    public Page<UsersAppDTO> AllUserspagination(int page) {
        Page<UsersApp> userPage = userRepo.findAll(PageRequest.of(page, 10)); // Adjust the page size as needed
        Page<UsersAppDTO> map = userPage.map((usersApp -> sihMapper.toUsersAppDTO(usersApp)));
        return map;
    }

    @Override
    public Page<UsersAppDTO> AllUsersByRolepaganation(int page, String rolesApp) {
        RolesApp byRolename = rolerepo.findByRolename(rolesApp);
        if(byRolename==null) throw new ApiRequestExpetion("Role not found");
        Page<UsersApp> userPage = userRepo.findUsersAppsByRoleApps(PageRequest.of(page, 10),byRolename); // Adjust the page size as needed
        Page<UsersAppDTO> map = userPage.map((usersApp -> sihMapper.toUsersAppDTO(usersApp)));
        return map;
    }

    @Override
    public Page<UsersAppDTO> AllUsersByCIN(int page, String cin) {
        Page<UsersApp> usersAppsByCNE = userRepo.findUsersAppsByCNE(PageRequest.of(page, 10), cin);
        if (usersAppsByCNE.isEmpty()) {
            throw new ApiRequestExpetion("User Not Found");
        }
        Page<UsersAppDTO> map = usersAppsByCNE.map((usersApp -> sihMapper.toUsersAppDTO(usersApp)));
        return map ;
    }

    @Override
    public void AddRoleToUser(String cin, String roleName) {
        UsersApp usersApp = userRepo.findById(cin).orElseThrow(() -> new ApiRequestExpetion("User not Found"));
        RolesApp rolesApp = rolerepo.findByRolename(roleName);
        if (rolesApp == null) {
            throw new ApiRequestExpetion("Role Not Found");
        }
        boolean userAlreadyHasRole = usersApp.getRoleApps().stream()
                .anyMatch(existingRole -> existingRole.getRolename().equals(roleName));
        if (userAlreadyHasRole) {
            throw new ApiRequestExpetion("User Already has the Role");
        }
        usersApp.getRoleApps().add(rolesApp);
        userRepo.save(usersApp);
    }

    @Override
    public void RemoveRoleToUser(String cin, String roleName) {
        UsersApp usersApp = userRepo.findById(cin).orElseThrow(() -> new ApiRequestExpetion("User not Found"));
        RolesApp rolesApp = rolerepo.findByRolename(roleName);
        if (rolesApp == null) {
            throw new ApiRequestExpetion("Role Not Found");
        }
        boolean userAlreadyHasRole = usersApp.getRoleApps().stream()
                .anyMatch(existingRole -> existingRole.getRolename().equals(roleName));
        if (!userAlreadyHasRole) {
            throw new ApiRequestExpetion("User Doesn't has the Role");
        }
        usersApp.getRoleApps().remove(rolesApp);
        userRepo.save(usersApp);
    }
    @Override
    public void SuspendeUser(String cin) {
        UsersApp usersApp = userRepo.findById(cin).orElseThrow(() -> new ApiRequestExpetion("User not Found"));
        if (!usersApp.getActive()) {
            throw new ApiRequestExpetion("User Already Suspende");
        }
        usersApp.setActive(false);
        userRepo.save(usersApp);
    }
    @Override
    public void ActiverUser(String cin) {
        UsersApp usersApp = userRepo.findById(cin).orElseThrow(() -> new ApiRequestExpetion("User not Found"));
        if (usersApp.getActive()) {
            throw new ApiRequestExpetion("User Already Active");
        }
        usersApp.setActive(true);
        userRepo.save(usersApp);
    }
    @Override
    public void DeleteUser(String cin) {
        UsersApp usersApp = userRepo.findById(cin).orElseThrow(() -> new ApiRequestExpetion("User not Found"));
        userRepo.delete(usersApp);
    }

    @Override
    public void AddUser(UsersAppDTO usersAppDTO) {
        UsersApp usersAppsByCNE = userRepo.findUsersAppsByCNE(usersAppDTO.getCNE());
        if(usersAppsByCNE!=null) throw new ApiRequestExpetion("USER with this CIN already existe");
        RolesApp byRolename = rolerepo.findByRolename(usersAppDTO.getRoleApps().get(0));
        if(byRolename==null) throw new ApiRequestExpetion("Role Not found");
        UsersApp usersApp = sihMapper.toUsersApp(usersAppDTO);
        userRepo.save(usersApp);
    }

    @Override
    public Long count() {
        return userRepo.count();
    }

}

