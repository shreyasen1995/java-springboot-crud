package com.ems.service.impl;

import com.ems.dto.EmployeeDto;
import com.ems.entity.Employee;
import com.ems.mapper.EmployeeMapper;
import com.ems.repository.EmployeeRepository;
import com.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    };

    @Override
    public EmployeeDto getEmployeeById(Long empId) {
        Employee savedEmployee = employeeRepository.findById(empId).orElseThrow(()->
                new ResourceAccessException("Employee with given id does not exist: " + empId));
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> list = employeeRepository.findAll();

        return list.stream().map(EmployeeMapper::mapToEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long empId, EmployeeDto updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(empId).orElseThrow(()->
                new ResourceAccessException("Employee with given id does not exist: " + empId));

        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setEmail(updatedEmployee.getEmail());

        Employee savedEmployee = employeeRepository.save(existingEmployee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public void deleteEmployee(Long empId) {
        employeeRepository.findById(empId).orElseThrow(()->
                new ResourceAccessException("Employee with given id does not exist: " + empId));
        employeeRepository.deleteById(empId);
    }

    ;
}
