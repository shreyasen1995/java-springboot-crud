package com.ems.controller;

import com.ems.dto.EmployeeDto;
import com.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    };

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long empId) {
        EmployeeDto employee = employeeService.getEmployeeById(empId);
        return ResponseEntity.ok(employee);
    };

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> list = employeeService.getAllEmployees();
        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long empId, @RequestBody EmployeeDto updatedEmployee) {
        EmployeeDto savedEmployee = employeeService.updateEmployee(empId, updatedEmployee);
        return ResponseEntity.ok(savedEmployee);
    };

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long empId) {
        employeeService.deleteEmployee(empId);
        return ResponseEntity.ok("Employee deleted successfully!");
    }
}
