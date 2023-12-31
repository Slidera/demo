package com.example.demo.controllers;

import com.example.demo.dtos.EmployeeDTO;
import com.example.demo.entities.Employee;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class employeeController {

  @Autowired
  private EmployeeService employeeService;

  @GetMapping
  public List<EmployeeDTO> getAllEmployees() {
    return employeeService.getAllEmployees().stream()

        .map(emp -> new EmployeeDTO(emp.getId(), emp.getName(), emp.getJobRole()))
        .collect(Collectors.toList());
  }

  @PostMapping
  public ResponseEntity<String> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
    Employee employee = new Employee(null, employeeDTO.getName(), employeeDTO.getJobRole());
    employeeService.addEmployee(employee);
    return ResponseEntity.ok("Employee added successfully");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
    boolean isRemoved = employeeService.deleteEmployee(id);
    if (!isRemoved) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with ID: " + id);
    }
    employeeService.deleteEmployee(id);
    return ResponseEntity.ok ("Employee deleted successfully");
  }
}
