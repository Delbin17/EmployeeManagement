package com.EmployeeManagement.Employees.employeeDto;


import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeRequest {

    private Long employeeId;
    private  String employeeName;
    private BigDecimal salary;
    private String position;
    private  String department;
    private String gender;
    private String password;



}
