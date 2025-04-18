package com.employment.service.impl;

import com.employment.form.CreateAdminForm;
import com.employment.form.CreateDayOffForm;
import com.employment.mapper.DayOffMap;
import com.employment.model.entity.DayOffData;
import com.employment.model.request.DayOffRequest;
import com.employment.repository.DayOffRepository;
import com.employment.repository.EmployeeRepository;
import com.employment.service.DayOffService;
import com.employment.validators.ValidateDayOff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Calendar;

@Service
public class DayOffServiceImpl implements DayOffService {
    @Autowired
    DayOffRepository dayOffRepository;

    @Autowired
    DayOffMap dayOffMap;

    @Autowired
    ValidateDayOff validateDayOff;

    @Override
    public DayOffData getDayOffById(Long id) {
        return dayOffRepository.getDayOffById(id);
    }

    @Override
    public List<DayOffData> getDayOffByEmployeeId(Long eid) {
        return dayOffRepository.getDayOffByEmployeeId(eid);
    }

    @Override
    public List<DayOffData> getAllDayOff() {
        return dayOffRepository.getAllDayOff();
    }

    @Override
    public void createDayOff(DayOffRequest request) throws Exception {
        validateDayOff.validate(request);
        dayOffRepository.createDayOff(request);
    }

    @Override
    public void updateDayOff(Long id, DayOffRequest request) {
        dayOffRepository.updateDayOff(id, request);
    }

    @Override
    public void deleteDayOff(Long id) {
        dayOffRepository.deleteDayOff(id);
    }

    @Override
    public void createDayOffFromForm(CreateDayOffForm form, BindingResult bindingResult) {
        DayOffRequest request = dayOffMap.map(form);
        try {
            validateDayOff.validate(request);
        } catch (Exception e) {
            FieldError error = new FieldError("createDayOffForm", "startDate", e.getMessage());
            bindingResult.addError(error);
        }

        if (!bindingResult.hasErrors()) {
            dayOffRepository.createDayOff(request);
        }
    }
}
