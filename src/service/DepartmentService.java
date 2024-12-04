package service;

import models.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartmentByHospital(Long id) throws Exception;

    Department findDepartmentByName(String name);
}
