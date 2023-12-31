package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
// Employee object
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String jobRole;

  // Default constructor
  public Employee() {
  }

  // Parameterized constructor
  public Employee(Long id, String name, String jobRole) {
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
