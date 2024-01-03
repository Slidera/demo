package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Employee;

@Repository
public class EmployeeDAO {
  private final JdbcTemplate jdbcTemplate;

  public EmployeeDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Employee> findAll() {
    try {
      return jdbcTemplate.query("SELECT * FROM employees", new EmployeeRowMapper());
    } catch (DataAccessException e) {
      throw new RuntimeException("Error retrieving employees", e);
    }
  }

  public void save(Employee employee) {
    try {
      jdbcTemplate.update("INSERT INTO employees (name, job_role) VALUES (?, ?)", employee.getName(),
          employee.getJobRole());
    } catch (DataAccessException e) {
      throw new RuntimeException("Error saving employee", e);
    }
  }

  public boolean deleteById(Long id) {
    try {
      Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM employees WHERE id = ?", Integer.class, id);
      if (count != null && count > 0) {
        jdbcTemplate.update("DELETE FROM employees WHERE id = ?", id);
        return true;
      }
      return false;
    } catch (DataAccessException e) {
      throw new RuntimeException("Error deleting employee with ID: " + id, e);
    }
  }

  private class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Employee(rs.getLong("id"), rs.getString("name"), rs.getString("job_role"));
    }
  }
}
