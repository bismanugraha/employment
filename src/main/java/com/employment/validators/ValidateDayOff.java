package com.employment.validators;

import com.employment.model.entity.DayOffData;
import com.employment.model.request.DayOffRequest;
import com.employment.repository.DayOffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class ValidateDayOff {
    @Autowired
    DayOffRepository dayOffRepository;

    public void validate(DayOffRequest request) throws Exception {
        long diffDateMill = request.getEndDate().getTime() - request.getStartDate().getTime();
        long diffDays = diffDateMill / (1000 * 60 * 60 * 24);
        if (diffDays > 0) {
            throw new Exception("Start date and end has been set more than 1 day!");
        }

        Calendar newStart = Calendar.getInstance();
        newStart.setTime(request.getStartDate());
        int newStartMonth = newStart.get(Calendar.MONTH);
        int newStartYear = newStart.get(Calendar.YEAR);

        Calendar newEnd = Calendar.getInstance();
        newEnd.setTime(request.getEndDate());
        int newEndMonth = newEnd.get(Calendar.MONTH);
        int newEndYear = newEnd.get(Calendar.YEAR);

        List<DayOffData> dayOffList = dayOffRepository.getDayOffByEmployeeId(request.getEmployeeId());
        long daysBetween = 0;
        for(DayOffData dayOffData: dayOffList) {
            Calendar existingStart = Calendar.getInstance();
            existingStart.setTime(dayOffData.getStartdate());
            int existStartMonth = existingStart.get(Calendar.MONTH);
            int existStartYear = existingStart.get(Calendar.YEAR);

            Calendar existingEnd = Calendar.getInstance();
            existingEnd.setTime(dayOffData.getEnddate());
            int existEndMonth = existingEnd.get(Calendar.MONTH);
            int existEndYear = existingEnd.get(Calendar.YEAR);

            boolean startOverlap = (newStartMonth == existStartMonth && newStartYear == existStartYear) ||
                    (newStartMonth == existEndMonth && newStartYear == existEndYear);

            boolean endOverlap = (newEndMonth == existStartMonth && newEndYear == existStartYear) ||
                    (newEndMonth == existEndMonth && newEndYear == existEndYear);

            if(startOverlap || endOverlap) {
                throw new Exception("Current employee already set in the same month in this year!");
            }

            daysBetween = daysBetween + ((dayOffData.getEnddate().getTime() - dayOffData.getStartdate().getTime()) / (1000 * 60 * 60 * 24)) + 1L;
        }

        if(daysBetween > 12) {
            throw new Exception("Current employee already use day off more than 12!");
        }
    }
}
