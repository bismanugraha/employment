package com.employment.repository.impl;

import com.employment.model.entity.DayOffData;
import com.employment.model.request.DayOffRequest;
import com.employment.repository.DayOffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DayOffRepositoryImpl implements DayOffRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    Logger LOG = LoggerFactory.getLogger(DayOffRepositoryImpl.class);

    private static final String GET_DAY_OFF_BY_ID = "SELECT id, eid, startdate, enddate, reason FROM dayoff WHERE id = :id";
    private static final String GET_DAY_OFF_BY_EMPLOYEE_ID = "SELECT id, eid, startdate, enddate, reason FROM dayoff WHERE eid = :eid";
    private static final String GET_DAY_OFFS = "SELECT id, eid, startdate, enddate, reason FROM dayoff";
    private static final String CREATE_DAY_OFF = "INSERT INTO dayoff (eid, startdate, enddate, reason) VALUES (:eid, :startdate, :enddate, :reason)";
    private static final String UPDATE_DAY_OFF = "UPDATE dayoff SET eid = :eid, startdate = :startdate, enddate = :enddate, reason = :reason WHERE id = :id";
    private static final String DELETE_DAY_OFF = "DELETE FROM dayoff WHERE id = :id";


    @Override
    public DayOffData getDayOffById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        DayOffData data = new DayOffData();
        try {
            data = namedParameterJdbcTemplate.queryForObject(GET_DAY_OFF_BY_ID, params, BeanPropertyRowMapper.newInstance(DayOffData.class));
        } catch (EmptyResultDataAccessException e) {
            LOG.error(e.getMessage());
        }
        return data;
    }

    @Override
    public List<DayOffData> getDayOffByEmployeeId(Long eid) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("eid", eid);
        List<DayOffData> data = new ArrayList<>();
        try {
            data = namedParameterJdbcTemplate.query(GET_DAY_OFF_BY_EMPLOYEE_ID, params, BeanPropertyRowMapper.newInstance(DayOffData.class));
        } catch (EmptyResultDataAccessException e) {
            LOG.error(e.getMessage());
        }
        return data;
    }

    @Override
    public List<DayOffData> getAllDayOff() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.query(GET_DAY_OFFS, params, BeanPropertyRowMapper.newInstance(DayOffData.class));
    }

    @Override
    public void createDayOff(DayOffRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("eid", request.getEmployeeId());
        params.addValue("startdate", request.getStartDate());
        params.addValue("enddate", request.getEndDate());
        params.addValue("reason", request.getReason());
        namedParameterJdbcTemplate.update(CREATE_DAY_OFF, params);
    }

    @Override
    public void updateDayOff(Long id, DayOffRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("eid", request.getEmployeeId());
        params.addValue("startdate", request.getStartDate());
        params.addValue("enddate", request.getEndDate());
        params.addValue("reason", request.getReason());
        namedParameterJdbcTemplate.update(UPDATE_DAY_OFF, params);
    }

    @Override
    public void deleteDayOff(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update(DELETE_DAY_OFF, params);
    }
}
