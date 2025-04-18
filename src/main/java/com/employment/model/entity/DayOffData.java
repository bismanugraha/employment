package com.employment.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
@Getter
@NoArgsConstructor
public class DayOffData {
    private Long id;
    @JsonProperty("employeeId")
    private Long eid;
    @JsonProperty("startDate")
    private Date startdate;
    @JsonProperty("endDate")
    private Date enddate;
    private String reason;
}
