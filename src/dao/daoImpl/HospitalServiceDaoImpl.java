package dao.daoImpl;

import dao.HospitalServiceDao;
import database.Database;
import models.Hospital;
import models.Patient;

import javax.xml.crypto.Data;
import java.util.*;


public class HospitalServiceDaoImpl implements HospitalServiceDao {


    @Override
    public String addHospital(Hospital hospital) {
        Database.hospitals.add(hospital);
        return "Больница ийгиликтүү сакталды:  "+hospital;
    }

    @Override
    public Hospital findHospitalById(Long id) {
        try {
            for (Hospital hospital:Database.hospitals){
                if (hospital.getId().equals(id)){
                    System.out.println(hospital);
                    return hospital;
                }
            }throw new Exception("Сиз издеген больницанын Id номери жок. Туура эмес жаздыныз!");
        } catch (Exception e){
            System.out.println("Ката: "+e.getMessage());
        }
        return null;
    }


    @Override
    public List<Hospital> getAllHospital() {
        return Database.hospitals;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        try {
            for (Hospital hospital:Database.hospitals){
                if (hospital.getId().equals(id)){
                    System.out.println(hospital.getPatients());
                }
            }throw new Exception("Мындай id жок!");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteHospitalById(Long id) throws Exception{
        try {
            for (Hospital hospital:Database.hospitals) {
                if (hospital.getId().equals(id)) {
                    Database.hospitals.remove(hospital);
                    System.out.println("Төмөндөү больница тизмеден йгиликтүү өчүрүлдү!\n"+hospital );
                }
            }throw new Exception("Мындай id номер тизмеден табылган жок!");
        } catch (Exception e){
            return "Ката: "+e.getMessage();
        }
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        try {
            Map<String, Hospital> result = new HashMap<>();
            for (Hospital hospital:  Database.hospitals) {
                if (hospital.getAddress().equalsIgnoreCase(address)) {
                    result.put(hospital.getAddress(),hospital);
                }
            }return result;
        } catch (Exception e){
            System.out.println("Ката: "+ e.getMessage());
        }
        return null;
    }
}
