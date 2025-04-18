package com.employment.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
@Getter
@NoArgsConstructor
public class DayOffRequest {
    private Long employeeId;
    private Date startDate;
    private Date endDate;
    private String reason;
}
