package com.employment.controller;

import com.employment.form.CreateAdminForm;
import com.employment.form.CreateDayOffForm;
import com.employment.form.CreateEmployeeForm;
import com.employment.form.LoginAdminForm;
import com.employment.model.entity.EmployeeData;
import com.employment.service.AdminService;
import com.employment.service.DayOffService;
import com.employment.service.EmployeeService;
import com.employment.validators.ValidateAuthorization;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Controller
public class WebController implements WebMvcConfigurer {

    @Autowired
    ValidateAuthorization validateAuthorization;

    @Autowired
    AdminService adminService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DayOffService dayOffService;

    Logger LOG = LoggerFactory.getLogger(WebController.class);

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("form");
        registry.addViewController("/employees").setViewName("employeeList");
        registry.addViewController("/employee/create").setViewName("createEmployee");
    }

    @GetMapping("/")
    public String showForm(LoginAdminForm loginAdminForm) {
        return "form";
    }

    @GetMapping("/createAdmin")
    public String showCreateForm(CreateAdminForm createAdminForm) {
        return "createAdmin";
    }

    @GetMapping("/employeeList")
    public String showEmployee(Model model) {
        List<EmployeeData> employeeDataList = employeeService.getAllEmployeeWithDayOffs();
        model.addAttribute("employeeList", employeeDataList);

        return "employeeList";
    }

    @GetMapping("/createEmployee")
    public String createEmployee(CreateEmployeeForm createEmployeeForm) {
        return "createEmployee";
    }

    @GetMapping("/createDayOff")
    public String createDayOff(CreateDayOffForm createDayOffForm) {
        return "createDayOff";
    }

    @GetMapping("/editEmployee/{id}")
    public String editEmployee(@PathVariable Long id, Model model, CreateEmployeeForm createEmployeeForm) {
        EmployeeData employee = employeeService.getEmployeeById(id);
        createEmployeeForm.setId(id);
        createEmployeeForm.setName(employee.getName());
        createEmployeeForm.setSurname(employee.getSurname());
        createEmployeeForm.setGender(employee.getGender());
        createEmployeeForm.setEmail(employee.getEmail());
        createEmployeeForm.setAddress(employee.getAddress());
        createEmployeeForm.setPhonenumber(employee.getPhonenumber());
//        model.addAttribute("employee", employee);
        return "editEmployee";
    }

    @PostMapping("/")
    public String login(Model model, @Valid LoginAdminForm loginAdminForm, BindingResult bindingResult) {
        validateAuthorization.validateLogin(loginAdminForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "form";
        }
        return "redirect:/employeeList";
    }

    @PostMapping("/createAdmin")
    public String createAdmin(@Valid CreateAdminForm createAdminForm, BindingResult bindingResult) {
        validateAuthorization.validateRegister(createAdminForm, bindingResult);

        try {
            if (!bindingResult.hasErrors()) {
                adminService.registerAdmin(createAdminForm);
            }
        } catch (Exception e) {
            ObjectError error = new ObjectError("formError", "Cannot create admin! " + e.getMessage());
            bindingResult.addError(error);
            LOG.error("Error! Cannot create admin! {}", e.getMessage());
        }
        if (bindingResult.hasErrors()) {
            return "createAdmin";
        }

        return "redirect:/form";
    }

    @PostMapping("/createEmployee")
    public String createEmployee(@Valid CreateEmployeeForm createEmployeeForm, BindingResult bindingResult) {
//        validateAuthorization.validateRegister(createAdminForm, bindingResult);

        try {
            if (!bindingResult.hasErrors()) {
                employeeService.registerEmployee(createEmployeeForm);
            }
        } catch (Exception e) {
            ObjectError error = new ObjectError("formError", "Cannot create employee! " + e.getMessage());
            bindingResult.addError(error);
            LOG.error("Error! Cannot create employee! {}", e.getMessage());
        }
        if (bindingResult.hasErrors()) {
            return "createEmployee";
        }

        return "redirect:/employeeList";
    }

    @PostMapping("/editEmployee")
    public String updateEmployee(@Valid CreateEmployeeForm createEmployeeForm, BindingResult bindingResult) {
//        validateAuthorization.validateRegister(createAdminForm, bindingResult);
        try {
            if (!bindingResult.hasErrors()) {
                employeeService.updateEmployeeFromForm(createEmployeeForm);
            }
        } catch (Exception e) {
            ObjectError error = new ObjectError("formError", "Cannot edit employee! " + e.getMessage());
            bindingResult.addError(error);
            LOG.error("Error! Cannot edit employee! {}", e.getMessage());
        }
        if (bindingResult.hasErrors()) {
            return "editEmployee";
        }

        return "redirect:/employeeList";
    }

    @PostMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employeeList";
    }

    @PostMapping("/createDayOff")
    public String createDayOff(@Valid CreateDayOffForm createDayOffForm, BindingResult bindingResult) {
//        validateAuthorization.validateRegister(createAdminForm, bindingResult);
        try {
            if (!bindingResult.hasErrors()) {
                dayOffService.createDayOffFromForm(createDayOffForm, bindingResult);
            }
        } catch (Exception e) {
            ObjectError error = new ObjectError("formError", "Cannot create day off! " + e.getMessage());
            bindingResult.addError(error);
            LOG.error("Error! Cannot create day off! {}", e.getMessage());
        }
        if (bindingResult.hasErrors()) {
            return "createDayOff";
        }

        return "redirect:/employeeList";
    }
}
