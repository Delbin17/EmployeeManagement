//package com.EmployeeManagement.Employees.employeeService;
//
//
//import com.EmployeeManagement.Employees.employeeDto.EmployeeResponse;
//import com.EmployeeManagement.Employees.employeeEntity.EmployeeDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.EmployeeManagement.Employees.employeeDto.SalaryUpdateResponse;
//import com.EmployeeManagement.Employees.employeeRepository.EmployeeRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public interface SalaryService {
//
//
//    @Autowired
//     EmployeeRepository employeerepo;
//
//    @Async  // Runs in a separate thread
//    public CompletableFuture<EmployeeResponse> incrementSalary() {
//        log.info("Starting salary increment process in thread: " + Thread.currentThread().getName());
//
//        List<EmployeeDetails> employees = employeeRepository.findAll();
//
//        if (employees.isEmpty()) {
//            return CompletableFuture.completedFuture(
//                    SalaryUpdateResponse.builder()
//                            .responseCode("404")
//                            .responseMessage("No employees found to update.")
//                            .build()
//            );
//        }
//
//        // Process employees in parallel
//        employees.parallelStream().forEach(employee -> {
//            BigDecimal increasedSalary = employee.getSalary().multiply(BigDecimal.valueOf(1.10)); // 10% increase
//            employee.setSalary(increasedSalary);
//            employeeRepository.save(employee);
//        });
//
//        log.info("Salary increment completed!");
//        return CompletableFuture.completedFuture(
//                SalaryUpdateResponse.builder()
//                        .responseCode("200")
//                        .responseMessage("Salaries incremented successfully by 10%.")
//                        .build()
//        );
//    }
//}