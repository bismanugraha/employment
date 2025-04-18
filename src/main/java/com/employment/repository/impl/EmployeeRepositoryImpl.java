package com.employment.repository.impl;

import com.employment.model.entity.EmployeeData;
import com.employment.model.request.EmployeeRequest;
import com.employment.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    Logger LOG = LoggerFactory.getLogger(EmployeeRepositoryImpl.class);

    private static final String GET_EMPLOYEE_BY_ID = "SELECT id, name, surname, email, phonenumber, address, gender FROM employee WHERE id = :id";
    private static final String GET_EMPLOYEES = "SELECT id, name, surname, email, phonenumber, address, gender FROM employee";
    private static final String CREATE_EMPLOYEE = "INSERT INTO employee (name, surname, email, phonenumber, address, gender) VALUES (:name, :surname, :email, :phonenumber, :address, :gender)";
    private static final String UPDATE_EMPLOYEE = "UPDATE employee SET name = :name, surname = :surname, email = :email, phonenumber = :phonenumber, address = :address, gender = :gender WHERE id = :id";
    private static final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE id = :id";


    @Override
    public EmployeeData getEmployeeById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        EmployeeData data = new EmployeeData();
        try {
            data = namedParameterJdbcTemplate.queryForObject(GET_EMPLOYEE_BY_ID, params, BeanPropertyRowMapper.newInstance(EmployeeData.class));
        } catch (EmptyResultDataAccessException e) {
            LOG.error(e.getMessage());
        }
        return data;
    }

    @Override
    public List<EmployeeData> getAllEmployee() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.query(GET_EMPLOYEES, params, BeanPropertyRowMapper.newInstance(EmployeeData.class));
    }

    @Override
    public void createEmployee(EmployeeRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", request.getName());
        params.addValue("surname", request.getSurname());
        params.addValue("email", request.getEmail());
        params.addValue("address", request.getAddress());
        params.addValue("gender", request.getGender());
        params.addValue("phonenumber", request.getPhonenumber());
        namedParameterJdbcTemplate.update(CREATE_EMPLOYEE, params);
    }

    @Override
    public void updateEmployee(Long id, EmployeeRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("name", request.getName());
        params.addValue("surname", request.getSurname());
        params.addValue("email", request.getEmail());
        params.addValue("address", request.getAddress());
        params.addValue("gender", request.getGender());
        params.addValue("phonenumber", request.getPhonenumber());
        namedParameterJdbcTemplate.update(UPDATE_EMPLOYEE, params);
    }

    @Override
    public void deleteEmployee(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update(DELETE_EMPLOYEE, params);
    }
}
