package com.employment.controller;

import com.employment.model.entity.AdminData;
import com.employment.model.request.AdminRequest;
import com.employment.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/admin/{id}")
    public ResponseEntity<?> getAdmin(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            AdminData data = adminService.getAdminById(id);
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

    @GetMapping("/admins")
    public ResponseEntity<?> getAdmins() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<AdminData> data = adminService.getAllAdmin();
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

    @PostMapping("/admin")
    public ResponseEntity<?> createAdmin(@RequestBody AdminRequest adminRequest) {
        Map<String, Object> response = new HashMap<>();
        try {
            adminService.createAdmin(adminRequest);
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "success");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateAdmin(@RequestBody AdminRequest adminRequest, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            adminService.updateAdmin(id, adminRequest);
            response.put("status", HttpStatus.ACCEPTED.value());
            response.put("message", "success");
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            adminService.deleteAdmin(id);
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
