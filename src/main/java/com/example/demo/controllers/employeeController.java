package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dtos.EmployeeDTO;
import com.example.demo.entities.Employee;
import com.example.demo.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping
  public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
    List<EmployeeDTO> employees = employeeService.getAllEmployees().stream()
        .map(emp -> new EmployeeDTO(emp.getId(), emp.getName(), emp.getJobRole()))
        .collect(Collectors.toList());
    return ResponseEntity.ok(employees);
  }

  @PostMapping
  public ResponseEntity<String> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
    Employee employee = new Employee(employeeDTO.getId(), employeeDTO.getName(), employeeDTO.getJobRole());
    employeeService.addEmployee(employee);
    return ResponseEntity.ok("Employee added successfully");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
    boolean isRemoved = employeeService.deleteEmployee(id);
    if (!isRemoved) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with ID: " + id);
    }
    return ResponseEntity.ok("Employee deleted successfully");
  }
}
