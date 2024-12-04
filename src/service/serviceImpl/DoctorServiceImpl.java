package service.serviceImpl;

import dao.DoctorServiceDao;
import dao.GenericServiceDao;
import dao.daoImpl.DoctorServiceDaoImpl;
import models.Doctor;
import service.DoctorService;
import service.GenericService;

import java.util.List;

public class DoctorServiceImpl implements DoctorService, GenericService<Doctor> {

    DoctorServiceDao doctorServiceDao = new DoctorServiceDaoImpl();
    GenericServiceDao<Doctor> genericServiceDao = new DoctorServiceDaoImpl();

    @Override
    public Doctor findDoctorById(Long id) {
        return doctorServiceDao.findDoctorById(id);
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        return doctorServiceDao.assignDoctorToDepartment(departmentId,doctorsId);
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        return doctorServiceDao.getAllDoctorsByHospitalId(id);
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        return doctorServiceDao.getAllDoctorsByDepartmentId(id);
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        return genericServiceDao.add(hospitalId,doctor);
    }

    @Override
    public void removeById(Long id) {
        genericServiceDao.removeById(id);
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        return genericServiceDao.updateById(id,doctor);
    }
}
