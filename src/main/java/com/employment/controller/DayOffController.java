package com.employment.controller;

import com.employment.model.entity.DayOffData;
import com.employment.model.request.DayOffRequest;
import com.employment.service.DayOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DayOffController {
    @Autowired
    DayOffService dayOffService;

    @GetMapping("/dayOff/{id}")
    public ResponseEntity<?> getDayOff(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            DayOffData data = dayOffService.getDayOffById(id);
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

    @GetMapping("/dayOff/employee/{id}")
    public ResponseEntity<?> getDayOffByEmployeeId(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<DayOffData> data = dayOffService.getDayOffByEmployeeId(id);
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

    @GetMapping("/dayOffs")
    public ResponseEntity<?> getDayOffs() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<DayOffData> data = dayOffService.getAllDayOff();
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

    @PostMapping("/dayOff")
    public ResponseEntity<?> createDayOff(@RequestBody DayOffRequest dayOffRequest) {
        Map<String, Object> response = new HashMap<>();
        try {
            dayOffService.createDayOff(dayOffRequest);
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "success");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/dayOff/{id}")
    public ResponseEntity<?> updateDayOff(@RequestBody DayOffRequest dayOffRequest, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            dayOffService.updateDayOff(id, dayOffRequest);
            response.put("status", HttpStatus.ACCEPTED.value());
            response.put("message", "success");
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/dayOff/{id}")
    public ResponseEntity<?> deleteDayOff(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            dayOffService.deleteDayOff(id);
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
