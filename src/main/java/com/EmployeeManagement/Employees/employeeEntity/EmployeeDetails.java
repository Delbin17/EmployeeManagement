package com.EmployeeManagement.Employees.employeeEntity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Employees")
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long employeeId;
    private  String employeeName;
    private BigDecimal salary;
    private String position;
    private  String department;
    private String gender;

    @UpdateTimestamp
    private LocalDate dateOfJoining;


}
