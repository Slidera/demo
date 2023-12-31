package com.example.demo.services;

import com.example.demo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.dao.EmployeeDAO;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeDAO employeeDao;

  public List<Employee> getAllEmployees() {
    return employeeDao.findAll();
  }

  public void addEmployee(Employee employee) {
    employeeDao.save(employee);
  }

  public boolean deleteEmployee(Long id) {
    return employeeDao.deleteById(id);
  }
}
