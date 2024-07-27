package ma.ensa.sihmoduleadmission.repos;

import ma.ensa.sihmoduleadmission.entities.Doctor;
import ma.ensa.sihmoduleadmission.entities.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor,String> {
    Page<Doctor> findDoctorBySpecialty(PageRequest pageRequest , Specialty Specialty);
    Page<Doctor> findDoctorByCNE(PageRequest pageRequest , String cne);
}
