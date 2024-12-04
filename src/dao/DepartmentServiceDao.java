package dao;

import models.Department;

import java.util.List;

public interface DepartmentServiceDao {

    List<Department> getAllDepartmentByHospital(Long id) throws Exception;

    Department findDepartmentByName(String name);
}
