package com.employment.service;

import com.employment.form.CreateAdminForm;
import com.employment.form.CreateDayOffForm;
import com.employment.model.entity.DayOffData;
import com.employment.model.request.DayOffRequest;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface DayOffService {
    DayOffData getDayOffById(Long id);
    List<DayOffData> getDayOffByEmployeeId(Long id);
    List<DayOffData> getAllDayOff();
    void createDayOff(DayOffRequest request) throws Exception;
    void updateDayOff(Long id, DayOffRequest request);
    void deleteDayOff(Long id);
    void createDayOffFromForm(CreateDayOffForm form, BindingResult bindingResult);
}
