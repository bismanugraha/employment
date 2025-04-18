package com.employment.repository;

import com.employment.model.entity.EmployeeData;
import com.employment.model.request.EmployeeRequest;

import java.util.List;

public interface EmployeeRepository {
    EmployeeData getEmployeeById(Long id);
    List<EmployeeData> getAllEmployee();
    void createEmployee(EmployeeRequest request);
    void updateEmployee(Long id, EmployeeRequest request);
    void deleteEmployee(Long id);
}
