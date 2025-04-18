package com.employment.mapper;

import com.employment.form.CreateAdminForm;
import com.employment.form.CreateEmployeeForm;
import com.employment.model.entity.EmployeeData;
import com.employment.model.request.AdminRequest;
import com.employment.model.request.EmployeeRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class EmployeeMap {
    public EmployeeRequest map(CreateEmployeeForm form) {
        EmployeeRequest request = new EmployeeRequest();
        request.setName(form.getName());
        request.setSurname(form.getSurname());
        request.setGender(form.getGender());
        request.setEmail(form.getEmail());
        request.setPhonenumber(form.getPhonenumber());
        request.setAddress(form.getAddress());

        return request;
    }
}
