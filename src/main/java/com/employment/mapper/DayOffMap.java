package com.employment.mapper;

import com.employment.form.CreateDayOffForm;
import com.employment.form.CreateEmployeeForm;
import com.employment.model.request.DayOffRequest;
import com.employment.model.request.EmployeeRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class DayOffMap {
    public DayOffRequest map(CreateDayOffForm form) {
        DayOffRequest request = new DayOffRequest();
        request.setEmployeeId(form.getEmployeeId());

        LocalDate localStartDate = LocalDate.of(form.getStartDate().getYear(), form.getStartDate().getMonth(), form.getStartDate().getDayOfMonth()); // example LocalDate
        Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        request.setStartDate(startDate);

        LocalDate localEndDate = LocalDate.of(form.getEndDate().getYear(), form.getEndDate().getMonth(), form.getEndDate().getDayOfMonth()); // example LocalDate
        Date endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        request.setEndDate(endDate);
        request.setReason(form.getReason());

        return request;
    }
}
