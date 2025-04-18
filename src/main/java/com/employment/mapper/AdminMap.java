package com.employment.mapper;

import com.employment.form.CreateAdminForm;
import com.employment.model.request.AdminRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class AdminMap {
    public AdminRequest map(CreateAdminForm form) {
        AdminRequest request = new AdminRequest();
        request.setName(form.getName());
        request.setSurname(form.getSurname());
        request.setEmail(form.getEmail());
        LocalDate localDate = LocalDate.of(form.getBirthdate().getYear(), form.getBirthdate().getMonth(), form.getBirthdate().getDayOfMonth()); // example LocalDate
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        request.setBirthdate(date);
        request.setPassword(form.getPassword());
        request.setGender(form.getGender());

        return request;
    }
}
