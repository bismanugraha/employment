package com.employment.service;

import com.employment.form.CreateEmployeeForm;
import com.employment.model.entity.EmployeeData;
import com.employment.model.request.EmployeeRequest;

import java.util.List;

public interface EmployeeService {
    EmployeeData getEmployeeById(Long id);
    List<EmployeeData> getAllEmployee();
    List<EmployeeData> getAllEmployeeWithDayOffs();
    void createEmployee(EmployeeRequest request);
    void updateEmployee(Long id, EmployeeRequest request);
    void deleteEmployee(Long id);
    void registerEmployee(CreateEmployeeForm form);
    void updateEmployeeFromForm(CreateEmployeeForm form);
}
