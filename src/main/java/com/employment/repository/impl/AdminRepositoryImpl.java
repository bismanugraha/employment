package com.employment.repository.impl;

import com.employment.model.entity.AdminData;
import com.employment.model.request.AdminRequest;
import com.employment.repository.AdminRepository;
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
public class AdminRepositoryImpl implements AdminRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    Logger LOG = LoggerFactory.getLogger(AdminRepositoryImpl.class);

    private static final String GET_ADMIN_BY_ID = "SELECT id, name, surname, email, birthdate, gender, password FROM admin WHERE id = :id";
    private static final String GET_ADMINS = "SELECT id, name, surname, email, birthdate, gender FROM admin";
    private static final String CREATE_ADMIN = "INSERT INTO admin (name, surname, email, birthdate, gender, password) VALUES (:name, :surname, :email, :birthdate, :gender, :password)";
    private static final String UPDATE_ADMIN = "UPDATE admin SET name = :name, surname = :surname, email = :email, birthdate = :birthdate, gender = :gender, password = :password WHERE id = :id";
    private static final String DELETE_ADMIN = "DELETE FROM admin WHERE id = :id";


    @Override
    public AdminData getAdminById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        AdminData data = new AdminData();
        try {
            data = namedParameterJdbcTemplate.queryForObject(GET_ADMIN_BY_ID, params, BeanPropertyRowMapper.newInstance(AdminData.class));
        } catch (EmptyResultDataAccessException e) {
            LOG.error(e.getMessage());
        }
        return data;
    }

    @Override
    public List<AdminData> getAllAdmin() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.query(GET_ADMINS, params, BeanPropertyRowMapper.newInstance(AdminData.class));
    }

    @Override
    public void createAdmin(AdminRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", request.getName());
        params.addValue("surname", request.getSurname());
        params.addValue("email", request.getEmail());
        params.addValue("birthdate", request.getBirthdate());
        params.addValue("gender", request.getGender());
        params.addValue("password", request.getPassword());
        namedParameterJdbcTemplate.update(CREATE_ADMIN, params);
    }

    @Override
    public void updateAdmin(Long id, AdminRequest request) throws Exception {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("name", request.getName());
        params.addValue("surname", request.getSurname());
        params.addValue("email", request.getEmail());
        params.addValue("birthdate", request.getBirthdate());
        params.addValue("gender", request.getGender());
        params.addValue("password", request.getPassword());
        int result = namedParameterJdbcTemplate.update(UPDATE_ADMIN, params);
        if(result == 0) {
            throw new Exception("Id not found!");
        }
    }

    @Override
    public void deleteAdmin(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update(DELETE_ADMIN, params);
    }
}
