package com.employment.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
public class EmployeeRequest {
    private String name;
    private String surname;
    private String email;
    private String phonenumber;
    private String address;
    private String gender;
}
