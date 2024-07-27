package ma.ensa.sihmoduleadmission.web;

import jakarta.validation.Valid;
import ma.ensa.sihmoduleadmission.dto.Medical_HistoryDTO;
import ma.ensa.sihmoduleadmission.dto.Medical_HistoryFromDoctorDTO;
import ma.ensa.sihmoduleadmission.entities.Medical_History;
import ma.ensa.sihmoduleadmission.mapper.SIHMapper;
import ma.ensa.sihmoduleadmission.service.appointment.AppointmentServiceImpl;
import ma.ensa.sihmoduleadmission.service.doctor.DoctorServicesImpl;
import ma.ensa.sihmoduleadmission.service.medicale_history.MedicalHistoryServiceImpl;
import ma.ensa.sihmoduleadmission.service.patient.PatientServicesImpl;
import ma.ensa.sihmoduleadmission.service.planification.PlanificationServicesImpl;
import ma.ensa.sihmoduleadmission.service.speciality.SpecialtyServicesImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@CrossOrigin
@Validated
public class Medicale_HestoryRestControleur {
    private PatientServicesImpl patientServicesImpl;
    private DoctorServicesImpl doctorServicesImpl;
    private MedicalHistoryServiceImpl medicalHistoryServiceImpl;
    private AppointmentServiceImpl appointmentServiceImpl;
    private PlanificationServicesImpl planificationServicesImpl;
    private SpecialtyServicesImpl specialtyServicesImpl;
    private SIHMapper sihMapper;

    public Medicale_HestoryRestControleur(PatientServicesImpl patientServices, DoctorServicesImpl doctorServices,
                                          MedicalHistoryServiceImpl medicalHistoryService, AppointmentServiceImpl appointmentService,
                                          PlanificationServicesImpl planificationServices, SpecialtyServicesImpl specialtyServices, SIHMapper sihMapper) {
        this.patientServicesImpl = patientServices;
        this.doctorServicesImpl = doctorServices;
        this.medicalHistoryServiceImpl = medicalHistoryService;
        this.appointmentServiceImpl = appointmentService;
        this.planificationServicesImpl = planificationServices;
        this.specialtyServicesImpl = specialtyServices;
        this.sihMapper = sihMapper;
    }
    @GetMapping("chu/patient/medical-histoey/{patientId}")
    public List <Medical_HistoryDTO> medicalHistoryDTO(@PathVariable(name = "patientId") String patientId){
        List<Medical_History> medicalHistories = medicalHistoryServiceImpl.findbypatientid(patientId);
        List<Medical_HistoryDTO> medicalHistoryDTOS = medicalHistories.stream().map(medicalHistory -> sihMapper.Medical_HistoryToDTOMedical_History(medicalHistory)
        ).collect(Collectors.toList());
        return medicalHistoryDTOS;
    }
    @PostMapping("chu/doctor/AddMedical-history")
    public void addMedcilaToTHeHistory (@Valid @RequestBody Medical_HistoryFromDoctorDTO medicalHistory){
         medicalHistoryServiceImpl.add(medicalHistory);
    }
}
