package com.EmployeeManagement.Employees.employeeRepository;


import com.EmployeeManagement.Employees.employeeEntity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetails ,Long> {

    boolean existsByEmployeeName(String employeeName);

    boolean existsById(long employeeId);

    EmployeeDetails findById(long employeeId);



}
