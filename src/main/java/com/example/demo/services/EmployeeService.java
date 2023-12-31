package com.example.demo.services;

import com.example.demo.entities.Employee;
import com.example.demo.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeRepository employeeRepository;
  // private Long nextId = 1L;

  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  public void addEmployee(Employee employee) {
    employeeRepository.save(employee);
  }

  public boolean deleteEmployee(Long id) {
    if (employeeRepository.existsById(id)) {
      employeeRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
    // employees.removeIf(employee -> employee.getId().equals(id));

  }
}
// public class EmployeeService {
// private final List<Employee> employees = new ArrayList<>();
// private Long nextId = 1L;

// public List<Employee> getAllEmployees() {
// return employees;
// }

// public void addEmployee(Employee employee) {
// employee.setId(nextId++);
// employees.add(employee);
// }

// public boolean deleteEmployee(Long id) {
// boolean exists = employees.stream().anyMatch(employee ->
// employee.getId().equals(id));
// if (exists) {
// employees.removeIf(employee -> employee.getId().equals(id));
// return true;
// } else {
// return false;
// }
// // employees.removeIf(employee -> employee.getId().equals(id));

// }
// }
