package dao.daoImpl;

import dao.GenericServiceDao;
import dao.PatientServiceDao;
import database.Database;
import models.Doctor;
import models.Hospital;
import models.Patient;

import java.util.*;
import java.util.stream.Collectors;

public class PatientServiceDaoImpl implements PatientServiceDao, GenericServiceDao<Patient> {



    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        try {
            for (Hospital hospital : Database.hospitals) {
                if (hospital.getId().equals(id)) {
                    hospital.getPatients().addAll(patients);
                    return "Бейтап больницага ийгиликтүү кошулду! ";
                }
            } throw new Exception("Ката жазылды");
        } catch (Exception e) {
            return "Сиз терген номер больницанын Id номерине дал келбейт! ";
        }
    }

    @Override
    public Patient getPatientById(Long id) {
        try {
            for (Hospital hospital : Database.hospitals) {
                for (Patient patient : hospital.getPatients()) {
                    if (patient.getId().equals(id)) {
                        return patient;
                    }
                }
            } throw new Exception("Сиз терген Id номер менен бейтап табылган жок! ");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Integer, Patient> getPatientByAge() {
        try {
            Map<Integer, Patient> patientsByAge = new HashMap<>();
            for (Hospital hospital : Database.hospitals) {
                for (Patient patient : hospital.getPatients()) {
                    patientsByAge.put(patient.getAge(), patient);
                }
            }
            return patientsByAge;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        List<Patient> allPatients = new ArrayList<>();
        for (Hospital hospital : Database.hospitals) {
            allPatients.addAll(hospital.getPatients());
        }
        if ("asc".equalsIgnoreCase(ascOrDesc)) {
            return allPatients.stream()
                    .sorted(Comparator.comparingInt(Patient::getAge))
                    .collect(Collectors.toList());
        } else if ("desc".equalsIgnoreCase(ascOrDesc)) {
            return allPatients.stream()
                    .sorted((p1, p2) -> Integer.compare(p2.getAge(), p1.getAge()))
                    .collect(Collectors.toList());
        } else {
            return allPatients;
        }
    }


    @Override
    public String add(Long hospitalId, Patient patient) {
        try {
            for (Hospital hospital: Database.hospitals){
                if (hospital.getId().equals(hospitalId)){
                    hospital.getPatients().add(patient);
                    return "Жаны бейтап ийгиликтүү кошулду";
                }
            }
            throw new Exception("id табылган жок!");
        }catch (Exception e){
            return "Ката : "+ e.getMessage();
        }
    }


    @Override
    public void removeById(Long id) {
        try {
            for (Hospital hospital:Database.hospitals){
                for (Patient patient: hospital.getPatients()) {
                    if (patient.getId().equals(id)){
                        hospital.getPatients().remove(patient);
                        System.out.println(patient+ "ийгиликтүү өчүрүлдү! ");
                    }

                }
            }throw new RuntimeException("Id номерди туура эмес жаздыныз!");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String updateById(Long id, Patient patient) {
            try {
                for (Hospital hospital:Database.hospitals){
                    for (Patient oldPatient:hospital.getPatients()){
                        if (oldPatient.getId().equals(id)){
                            oldPatient.setFirstName(patient.getFirstName());
                            oldPatient.setLastName(patient.getFirstName());
                            oldPatient.setAge(patient.getAge());
                            oldPatient.setGender(patient.getGender());
                            return "Бейтаптын өзгөртүүлөрү ийгиликтүү аткарылды!";
                        }
                    }
                }throw new Exception("Мындай Id номер катталган эмес!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }
}
