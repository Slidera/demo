package com.example.demo.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployeeDTO {
  private Long id;

  @NotNull(message="Name cannot be null")
  @Size(min = 2, message = "Name should have at least 2 characters")
  private String name;

  @NotNull(message = "Job role cannot be null")
  private String jobRole;

  // Default constructor
  public EmployeeDTO() {
  }

  // Parameterized constructor
  public EmployeeDTO(Long id, String name, String jobRole) {
    this.id = id;
    this.name = name;
    this.jobRole = jobRole;
  }

  // Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getJobRole() {
    return jobRole;
  }

  public void setJobRole(String jobRole) {
    this.jobRole = jobRole;
  }

}
