package com.example.demo.dao;

import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.example.demo.entities.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class EmployeeDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public EmployeeDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Employee> findAll() {
    return jdbcTemplate.query("SELECT * FROM employees", new EmployeeRowMapper());
  }

  public void save(Employee employee) {
    jdbcTemplate.update("INSERT INTO employees (name, job_role) VALUES (?, ?)", employee.getName(),
        employee.getJobRole());
  }

  public boolean deleteById(Long id) {
    int rowsAffected = jdbcTemplate.update("DELETE FROM employees WHERE id = ?", id);
    return rowsAffected > 0;
  }

  private class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Employee(rs.getLong("id"), rs.getString("name"), rs.getString("job_role"));
    }
  }
}
