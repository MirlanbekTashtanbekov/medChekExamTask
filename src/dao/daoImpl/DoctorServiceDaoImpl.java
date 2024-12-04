package dao.daoImpl;

import dao.DoctorServiceDao;
import dao.GenericServiceDao;
import database.Database;
import models.Department;
import models.Doctor;
import models.Hospital;

import java.util.ArrayList;
import java.util.List;

import static database.GenerateId.hospitalId;

public class DoctorServiceDaoImpl implements DoctorServiceDao, GenericServiceDao<Doctor>{


    @Override
    public Doctor findDoctorById(Long id) {
        try {
            for (Hospital hospital : Database.hospitals){
                for (Doctor doctor: hospital.getDoctors()){
                    if (doctor.getId().equals(id)){
                        return doctor;
                    }
                }
            }throw new Exception("Мындай Id номер катоодон өткөн эмес!");
        }catch (Exception e){
            System.out.println("Ката: "+e.getMessage());
        }
        return null;
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        try {
            for (Hospital hospital : Database.hospitals) {
                for (Department department : hospital.getDepartments()) {
                    if (department.getId().equals(departmentId)) {
                        List<Doctor> doctors = new ArrayList<>();
                        for (Long doctorId : doctorsId) {
                            Doctor doctor = findDoctorById(doctorId);
                            if (doctor != null&& !department.getDoctors().contains(doctor)) {
                                department.getDoctors().add(doctor);
                            }
                        }
                        department.setDoctors(doctors);
                        return "Врач бөлүмгө дайындалды! ";
                    }
                }
            }
            throw new Exception("Сиз жазган id номер менен бөлүм табылган жок! ");
        } catch (Exception e) {
            return "Ката: " + e.getMessage();
        }
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        try {
            for (Hospital hospital:Database.hospitals){
                if (hospital.getId().equals(id)){
                    return hospital.getDoctors();
                }
            }throw new Exception("Id номерди туура эмес жаздыныз!");
        }catch (Exception e){
            System.out.println("Ката: "+e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        try{
            for (Hospital hospital:Database.hospitals){
                for (Department department:hospital.getDepartments()){
                    if (department.getId().equals(id)){
                        return department.getDoctors();
                    }
                }
            } throw new Exception("Id номерди туура эмес жаздыныз!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String add(Long departmentId, Doctor doctor) {
        try {
            for (Hospital hospital:Database.hospitals){
                for (Department department:hospital.getDepartments()){
                    if (department.getId().equals(departmentId)){
                        department.getDoctors().add(doctor);
                        return "Жаны врач ийгилитүү кошулду! ";
                    }
                }
            }
            throw  new Exception("Id номерди туура эмес жаздыныз!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void removeById(Long id) {
        try {
            for (Hospital hospital:Database.hospitals){
                for (Doctor doctor: hospital.getDoctors()) {
                    if (doctor.getId().equals(id)){
                        hospital.getDoctors().remove(doctor);
                        System.out.println(doctor+ "ийгиликтүү өчүрүлдү! ");
                    }

                }
            }throw new RuntimeException("Id номерди туура эмес жаздыныз!");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        try {
            for (Hospital hospital:Database.hospitals){
                for (Doctor oldDoctor:hospital.getDoctors()){
                    if (oldDoctor.getId().equals(id)){
                        oldDoctor.setFirstName(doctor.getFirstName());
                        oldDoctor.setLastName(doctor.getFirstName());
                        oldDoctor.setGender(doctor.getGender());
                        oldDoctor.setExperienceYear(doctor.getExperienceYear());
                        return "Врачтын өзгөртүүлөрү ийгиликтүү аткарылды!";
                    }
                }
            }throw new Exception("Мындай Id номер катталган эмес!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
