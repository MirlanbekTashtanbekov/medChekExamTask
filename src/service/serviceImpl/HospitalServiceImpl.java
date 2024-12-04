package service.serviceImpl;

import dao.HospitalServiceDao;
import dao.daoImpl.HospitalServiceDaoImpl;
import models.Hospital;
import models.Patient;
import service.HospitalService;

import java.util.List;
import java.util.Map;

public class HospitalServiceImpl implements HospitalService {

    private final HospitalServiceDao hospitalServiceDao = new HospitalServiceDaoImpl();

    @Override
    public String addHospital(Hospital hospital) {
        return hospitalServiceDao.addHospital(hospital);
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return hospitalServiceDao.findHospitalById(id);

    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalServiceDao.getAllHospital();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return hospitalServiceDao.getAllPatientFromHospital(id);
    }

    @Override
    public String deleteHospitalById(Long id) throws Exception {
        return hospitalServiceDao.deleteHospitalById(id);
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        return hospitalServiceDao.getAllHospitalByAddress(address);
    }
}
