package com.EmployeeManagement.Employees.employeesController;


import com.EmployeeManagement.Employees.employeeDto.EmployeeRequest;
import com.EmployeeManagement.Employees.employeeDto.EmployeeResponse;
import com.EmployeeManagement.Employees.employeeDto.EmployeeUpdateRequest;
import com.EmployeeManagement.Employees.employeeDto.IdRequest;
import com.EmployeeManagement.Employees.employeeEntity.EmployeeDetails;
import com.EmployeeManagement.Employees.employeeService.EmployeeService;
import com.EmployeeManagement.Employees.employeeService.SalarayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

@Autowired
    SalarayServiceImpl service;
    @PostMapping("/addEmployee")
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest employeeRequest){
        return employeeService.addEmployee(employeeRequest);
    }


    @GetMapping("/findEmpById")
    public ResponseEntity<EmployeeResponse> findEmployeeById(@RequestBody IdRequest idRequest){
        EmployeeResponse employeeResponse=employeeService.employeeById(idRequest);
        return ResponseEntity.ok(employeeResponse);
    }

    @PostMapping("/Update")
    public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody EmployeeUpdateRequest employeeUpdateRequest){
        EmployeeResponse employeeResponse  =employeeService.updateEmployee(employeeUpdateRequest);
        return ResponseEntity.ok(employeeResponse);
    }

    @GetMapping("Delete")
    public EmployeeResponse deleteEmployee(@RequestBody IdRequest idRequest){
        return  employeeService.deleteById(idRequest);
    }

    @PutMapping("/increment-salary")
    public CompletableFuture<ResponseEntity<EmployeeResponse>> incrementAllSalaries() {
        return service.incrementSalary()
                .thenApply(response -> ResponseEntity.ok(response));
    }


}
