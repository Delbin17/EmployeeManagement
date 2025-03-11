package com.EmployeeManagement.Employees.employeeDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse {

    private String responseCode;
    private String responseMessage;
    private  EmployeeInfo employeeInfo;

}
