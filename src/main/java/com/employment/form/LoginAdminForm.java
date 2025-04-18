package com.employment.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginAdminForm {
    @NotNull
    private Long id;
    @NotNull
    private String password;
}
