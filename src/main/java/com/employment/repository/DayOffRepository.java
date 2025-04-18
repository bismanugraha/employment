package com.employment.repository;

import com.employment.model.entity.DayOffData;
import com.employment.model.request.DayOffRequest;

import java.util.List;

public interface DayOffRepository {
    DayOffData getDayOffById(Long id);
    List<DayOffData> getDayOffByEmployeeId(Long eid);
    List<DayOffData> getAllDayOff();
    void createDayOff(DayOffRequest request);
    void updateDayOff(Long id, DayOffRequest request);
    void deleteDayOff(Long id);
}
