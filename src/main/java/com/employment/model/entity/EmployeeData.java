package com.employment.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
public class EmployeeData {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phonenumber;
    private String address;
    private String gender;
    private List<DayOffData> dayOffs;
}
