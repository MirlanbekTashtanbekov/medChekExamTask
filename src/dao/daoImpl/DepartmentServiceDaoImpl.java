package dao.daoImpl;

import dao.DepartmentServiceDao;
import dao.GenericServiceDao;
import database.Database;
import models.Department;
import models.Hospital;

import java.util.ArrayList;
import java.util.List;

public class DepartmentServiceDaoImpl implements DepartmentServiceDao , GenericServiceDao<Department> {



    @Override
    public List<Department> getAllDepartmentByHospital(Long id) throws Exception{
        try {
            for (Hospital hospital : Database.hospitals){
                if (hospital.getId().equals(id)){
                    return hospital.getDepartments();
                }
            }throw new Exception("Мындай Id номер катталган эмес!");
        }catch (Exception e){
            System.out.println("Ката: "+e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Department findDepartmentByName(String name) {
        try {
            for (Hospital hospital: Database.hospitals){
                for (Department department: hospital.getDepartments()){
                    if (department.getDepartmentName().equalsIgnoreCase(name)){
                        return department;
                    }
                }
            }throw new Exception("Бөлүмдүн аталышы туура эмес жазылды!");

        }catch (Exception e){
            System.out.println("Ката болуп калды: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String add(Long hospitalId, Department department) {
        try {
            for (Hospital hospital: Database.hospitals){
                if (hospital.getId().equals(hospitalId)){
                    hospital.getDepartments().add(department);
                    return "Жаны бөлүм ийгиликтүү кошулду";
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
            for (Hospital hospital: Database.hospitals){
                for (Department department: hospital.getDepartments()){
                    if (department.getId().equals(id)){
                        hospital.getDepartments().remove(department);
                        System.out.println("Бөлүм ийгиликтүү өчүрүлдү тизмеден");
                    }
                }
            }throw new Exception("Мындай Id номер катталган эмес!");

        } catch (Exception e){
            System.out.println("Ката : "+e.getMessage());
        }

    }

    @Override
    public String updateById(Long id, Department department) {
        try {
            for (Hospital hospital: Database.hospitals){
                for (Department oldDepartment: hospital.getDepartments()){
                    if (oldDepartment.getId().equals(id)){
                        oldDepartment.setDepartmentName(department.getDepartmentName());
                        return "Бөлүмдүн өзгөртүүлөрү ийгиликтүү аткарылды! ";
                    }
                }
            }throw new Exception("Мындай Id номер катталган эмес!");
        } catch (Exception e) {
            return "Ката: "+ e.getMessage();
        }

    }
}
