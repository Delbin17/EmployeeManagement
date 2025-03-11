package com.EmployeeManagement.Employees.employeeService;


import com.EmployeeManagement.Employees.employeeDto.EmployeeRequest;
import com.EmployeeManagement.Employees.employeeDto.EmployeeResponse;
import com.EmployeeManagement.Employees.employeeDto.EmployeeUpdateRequest;
import com.EmployeeManagement.Employees.employeeDto.IdRequest;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

     EmployeeResponse addEmployee(EmployeeRequest employeeRequest);

     EmployeeResponse employeeById(IdRequest idRequest);

     EmployeeResponse updateEmployee(EmployeeUpdateRequest employeeUpdateRequest);

     EmployeeResponse deleteById(IdRequest idRequest);


}
