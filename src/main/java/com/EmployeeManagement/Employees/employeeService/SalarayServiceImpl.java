package com.EmployeeManagement.Employees.employeeService;


import com.EmployeeManagement.Employees.employeeDto.EmployeeResponse;
import com.EmployeeManagement.Employees.employeeEntity.EmployeeDetails;
import com.EmployeeManagement.Employees.employeeRepository.EmployeeRepository;
import com.EmployeeManagement.Employees.employeeUtills.EmployeeUtills;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
@RequiredArgsConstructor
@Slf4j
public class SalarayServiceImpl  {

    private final EmployeeRepository employeeRepository;

    @Async
    public CompletableFuture<EmployeeResponse> incrementSalary() {
        log.info("Starting salary increment process in thread: " + Thread.currentThread().getName());

        List<EmployeeDetails> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            return CompletableFuture.completedFuture(
                    EmployeeResponse.builder()
                            .responseCode(EmployeeUtills.Employee_NotExisted_CODE)
                            .responseMessage(EmployeeUtills.Employee_NotExisted_MESSAGE)
                            .build()
            );
        }

        employees.parallelStream().forEach(employee -> {
            BigDecimal increasedSalary = employee.getSalary().multiply(BigDecimal.valueOf(1.10));
            employee.setSalary(increasedSalary);
            employeeRepository.save(employee);
        });

        log.info("Salary increment completed!");
        return CompletableFuture.completedFuture(
                EmployeeResponse.builder()
                        .responseCode(EmployeeUtills.Salary_Updated_CODE)
                        .responseMessage(EmployeeUtills.Salary_Updated_MESSAGE)
                        .build()
        );
    }
}
