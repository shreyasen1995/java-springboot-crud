package com.ems.service;

import com.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long empId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(Long empId, EmployeeDto updatedEmployee);
    void deleteEmployee(Long empId);
}
