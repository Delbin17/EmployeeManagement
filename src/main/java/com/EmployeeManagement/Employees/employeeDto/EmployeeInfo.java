package com.EmployeeManagement.Employees.employeeDto;


import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeInfo {

    private String employeeName;
    private String gender;
    private  String position;
    private String department;
    private BigDecimal salary;

    private LocalDate dateOfJoining;

}
