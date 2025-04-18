package com.employment.validators;

import com.employment.form.CreateAdminForm;
import com.employment.form.LoginAdminForm;
import com.employment.model.entity.AdminData;
import com.employment.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
public class ValidateAuthorization {
    @Autowired
    AdminService adminService;

    public void validateLogin(LoginAdminForm adminForm, BindingResult bindingResult) {
        AdminData data = adminService.getAdminById(adminForm.getId());

        if(data.getId() == null) {
            FieldError error = new FieldError("loginAdminForm", "id", "User not found!");
            bindingResult.addError(error);
            return;
        }
        if(!data.getPassword().equals(adminForm.getPassword())) {
            FieldError error = new FieldError("loginAdminForm", "password", "Password doesn't match!");
            bindingResult.addError(error);
        }
    }

    public void validateRegister(CreateAdminForm adminForm, BindingResult bindingResult) {

    }
}
