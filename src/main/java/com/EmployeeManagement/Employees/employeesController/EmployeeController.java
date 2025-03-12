package com.EmployeeManagement.Employees.employeesController;


import com.EmployeeManagement.Employees.employeeDto.EmployeeRequest;
import com.EmployeeManagement.Employees.employeeDto.EmployeeResponse;
import com.EmployeeManagement.Employees.employeeDto.EmployeeUpdateRequest;
import com.EmployeeManagement.Employees.employeeDto.IdRequest;
import com.EmployeeManagement.Employees.employeeService.EmployeeService;
import com.EmployeeManagement.Employees.employeeService.SalarayServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
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

    @GetMapping("/")
    public  String Token(HttpServletRequest request){
        return "Token"+request.getSession().getId();
    }

    @GetMapping("/findEmpById")
    public ResponseEntity<EmployeeResponse> findEmployeeById(@RequestBody IdRequest idRequest){
        EmployeeResponse employeeResponse=employeeService.employeeById(idRequest);
        return ResponseEntity.ok(employeeResponse);
    }


    @GetMapping("/csrf-Token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
    @PostMapping("/Update")
    public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody EmployeeUpdateRequest employeeUpdateRequest){
        EmployeeResponse employeeResponse  =employeeService.updateEmployee(employeeUpdateRequest);
        return ResponseEntity.ok(employeeResponse);
    }
    @DeleteMapping("Delete/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId   ){
        return  employeeService.deleteById(employeeId);
    }

    @PutMapping("/increment-salary")
    public CompletableFuture<ResponseEntity<EmployeeResponse>> incrementAllSalaries() {
        return service.incrementSalary()
                .thenApply(ResponseEntity::ok);
    }


}
