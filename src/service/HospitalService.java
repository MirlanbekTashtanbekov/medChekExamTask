package service;

import models.Hospital;
import models.Patient;

import java.util.List;
import java.util.Map;

public interface HospitalService {

    String addHospital(Hospital hospital);

    Hospital findHospitalById(Long id);

    List<Hospital> getAllHospital();

    List<Patient> getAllPatientFromHospital(Long id);

    String deleteHospitalById(Long id) throws Exception;

    Map<String, Hospital> getAllHospitalByAddress(String address);

}
