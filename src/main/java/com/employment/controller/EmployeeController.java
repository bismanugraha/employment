package com.employment.controller;

import com.employment.model.entity.EmployeeData;
import com.employment.model.request.EmployeeRequest;
import com.employment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            EmployeeData data = employeeService.getEmployeeById(id);
            response.put("status", HttpStatus.OK.value());
            response.put("message", "success");
            response.put("data", data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getEmployees() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<EmployeeData> data = employeeService.getAllEmployee();
            response.put("status", HttpStatus.OK.value());
            response.put("message", "success");
            response.put("data", data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/employees/dayOff")
    public ResponseEntity<?> getEmployeesWithDayOff() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<EmployeeData> data = employeeService.getAllEmployeeWithDayOffs();
            response.put("status", HttpStatus.OK.value());
            response.put("message", "success");
            response.put("data", data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/employee")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Map<String, Object> response = new HashMap<>();
        try {
            employeeService.createEmployee(employeeRequest);
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "success");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeRequest employeeRequest, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            employeeService.updateEmployee(id, employeeRequest);
            response.put("status", HttpStatus.ACCEPTED.value());
            response.put("message", "success");
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            employeeService.deleteEmployee(id);
            response.put("status", HttpStatus.NO_CONTENT.value());
            response.put("message", "success");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
