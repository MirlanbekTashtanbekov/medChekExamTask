package service.serviceImpl;

import dao.GenericServiceDao;
import dao.PatientServiceDao;
import models.Patient;
import service.GenericService;
import service.PatientService;

import java.util.List;
import java.util.Map;

public class PatientServiceImpl implements PatientService, GenericService<Patient> {

    private PatientServiceDao patientServiceDao = new dao.daoImpl.PatientServiceDaoImpl();
    GenericServiceDao<Patient> genericServiceDao = new dao.daoImpl.PatientServiceDaoImpl();

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        return patientServiceDao.addPatientsToHospital(id,patients);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientServiceDao.getPatientById(id);
    }

    @Override
    public Map<Integer, Patient> getPatientByAge() {
        return patientServiceDao.getPatientByAge();
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        return patientServiceDao.sortPatientsByAge(ascOrDesc);
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        return genericServiceDao.add(hospitalId,patient);
    }

    @Override
    public void removeById(Long id) {
        genericServiceDao.removeById(id);
    }

    @Override
    public String updateById(Long id, Patient patient) {
        return genericServiceDao.updateById(id,patient);
    }
}
