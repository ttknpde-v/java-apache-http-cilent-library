package com.ttknpdev.controller;


import com.ttknpdev.entity.Employee;
import com.ttknpdev.service.ServiceEmployee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ControllerApi {
    private final ServiceEmployee serviceEmployee;

    public ControllerApi() {
        serviceEmployee = new ServiceEmployee();
    }

    @GetMapping(value = "/test")
    private String test() {
        return "hello world";
    }
    @GetMapping(value = "/read/{id}")
    private ResponseEntity<Employee> read(@PathVariable int id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(serviceEmployee.readById(id));
    }
    @GetMapping(value = "/reads")
    private ResponseEntity<List<Employee>> read() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(serviceEmployee.reads());
    }

    @PostMapping(value = "/create")
    private ResponseEntity<Boolean> create(@RequestBody  Employee employee) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(serviceEmployee.create(employee));
    }

    @PutMapping(value = "/update/{id}")
    private ResponseEntity<Boolean> update(@RequestBody  Employee employee,@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(serviceEmployee.update(id,employee));
    }

    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(serviceEmployee.delete(id));
    }
}
