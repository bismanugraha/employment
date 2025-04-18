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
public class AdminRequest {
    private String name;
    private String surname;
    private String email;
    private Date birthdate;
    private String gender;
    private String password;
}
