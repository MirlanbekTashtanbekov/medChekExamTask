package service.serviceImpl;

import dao.DepartmentServiceDao;
import dao.GenericServiceDao;
import dao.daoImpl.DepartmentServiceDaoImpl;
import models.Department;
import service.DepartmentService;
import service.GenericService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService , GenericService<Department> {

    DepartmentServiceDao departmentServiceDao = new DepartmentServiceDaoImpl();

    GenericServiceDao<Department> genericServiceDao = new DepartmentServiceDaoImpl();

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) throws Exception {
        return departmentServiceDao.getAllDepartmentByHospital(id);
    }

    @Override
    public Department findDepartmentByName(String name) {
        return departmentServiceDao.findDepartmentByName(name);
    }

    @Override
    public String add(Long hospitalId, Department department) {
        return genericServiceDao.add(hospitalId,department);
    }

    @Override
    public void removeById(Long id) {
        genericServiceDao.removeById(id);

    }

    @Override
    public String updateById(Long id, Department department) {
        return genericServiceDao.updateById(id, department);
    }
}
