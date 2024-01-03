package com.example.demo.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Employee;
import com.example.demo.dao.EmployeeDAO;

@Service
public class EmployeeService {
  private final EmployeeDAO employeeDAO;

  public EmployeeService(EmployeeDAO employeeDAO) {
    this.employeeDAO = employeeDAO;
  }

  public List<Employee> getAllEmployees() {
    return employeeDAO.findAll();
  }

  public void addEmployee(Employee employee) {
    employeeDAO.save(employee);
  }

  public boolean deleteEmployee(Long id) {
    return employeeDAO.deleteById(id);
  }
}
