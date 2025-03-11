package com.EmployeeManagement.Employees.employeeDto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class EmployeeUpdateRequest {
    private Long employeeId;
    private String employeeName;
    private String department;
    private String position;
    private Double salary;
    private String gender;
    private String dateOfJoining;
}
