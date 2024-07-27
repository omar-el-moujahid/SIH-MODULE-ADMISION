package ma.ensa.sihmoduleadmission.service.role;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.sihmoduleadmission.dto.PatientDTO;
import ma.ensa.sihmoduleadmission.entities.Patient;
import ma.ensa.sihmoduleadmission.entities.securiy.RolesApp;
import ma.ensa.sihmoduleadmission.expetion.ApiRequestExpetion;
import ma.ensa.sihmoduleadmission.expetion.thrabelauthontification;
import ma.ensa.sihmoduleadmission.mapper.SIHMapper;
import ma.ensa.sihmoduleadmission.repos.PatientRepo;
import ma.ensa.sihmoduleadmission.repos.Rolerepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class RoleServicesImpl implements RoleServices {
    private Rolerepo rolerepo;
    private SIHMapper sihMapper;
    private PasswordEncoder passwordEncoder;
    public RoleServicesImpl(Rolerepo rolerepo, SIHMapper sihMapper, PasswordEncoder passwordEncoder) {
        this.rolerepo = rolerepo;
        this.sihMapper = sihMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RolesApp findbyname(String s) {
        RolesApp byRolename = rolerepo.findByRolename(s);
        if(byRolename==null) throw new ApiRequestExpetion("Role not found");
        return byRolename;
    }
}
