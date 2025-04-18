package com.employment.service.impl;

import com.employment.form.CreateAdminForm;
import com.employment.form.CreateEmployeeForm;
import com.employment.mapper.EmployeeMap;
import com.employment.model.entity.DayOffData;
import com.employment.model.entity.EmployeeData;
import com.employment.model.request.EmployeeRequest;
import com.employment.repository.DayOffRepository;
import com.employment.repository.EmployeeRepository;
import com.employment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DayOffRepository dayOffRepository;
    @Autowired
    EmployeeMap employeeMap;

    @Override
    public EmployeeData getEmployeeById(Long id) {
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public List<EmployeeData> getAllEmployee() {
        return employeeRepository.getAllEmployee();
    }

    @Override
    public List<EmployeeData> getAllEmployeeWithDayOffs() {
        List<EmployeeData> employees = employeeRepository.getAllEmployee();
        for(EmployeeData employee : employees) {
            employee.setDayOffs(dayOffRepository.getDayOffByEmployeeId(employee.getId()));
        }
        return employees;
    }

    @Override
    public void createEmployee(EmployeeRequest request) {
        employeeRepository.createEmployee(request);
    }

    @Override
    public void updateEmployee(Long id, EmployeeRequest request) {
        employeeRepository.updateEmployee(id, request);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteEmployee(id);
    }

    @Override
    public void registerEmployee(CreateEmployeeForm form) {
        EmployeeRequest request = employeeMap.map(form);
        employeeRepository.createEmployee(request);
    }

    @Override
    public void updateEmployeeFromForm(CreateEmployeeForm form) {
        EmployeeRequest request = employeeMap.map(form);
        employeeRepository.updateEmployee(form.getId(), request);
    }
}
