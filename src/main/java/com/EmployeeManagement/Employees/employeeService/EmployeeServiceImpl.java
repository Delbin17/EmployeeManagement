package com.EmployeeManagement.Employees.employeeService;

import com.EmployeeManagement.Employees.employeeDto.*;
import com.EmployeeManagement.Employees.employeeEntity.EmployeeDetails;
import com.EmployeeManagement.Employees.employeeRepository.EmployeeRepository;
import com.EmployeeManagement.Employees.employeeUtills.EmployeeUtills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) {

        if (employeeRepository.existsByEmployeeName(employeeRequest.getEmployeeName())) {
            return EmployeeResponse.builder()
                    .responseCode(EmployeeUtills.Employee_EXIST_CODE)
                    .responseMessage(EmployeeUtills.Employee_EXIST_MESSAGE)
                    .employeeInfo(null)
                    .build();
        }

        EmployeeDetails employee = EmployeeDetails.builder()

                .employeeName(employeeRequest.getEmployeeName())
                .department(employeeRequest.getDepartment())
                .position(employeeRequest.getPosition())
                .salary(employeeRequest.getSalary())
                .gender(employeeRequest.getGender())
                .build();
        EmployeeDetails SaveEmployee = employeeRepository.save(employee);


        return EmployeeResponse.builder()

                .responseCode(EmployeeUtills.Employee_Created_CODE)
                .responseMessage(EmployeeUtills.Employee_Created_MESSAGE)
                .employeeInfo(EmployeeInfo.builder()
                        .employeeName(employeeRequest.getEmployeeName())
                        .gender(employeeRequest.getGender())
                        .position(employeeRequest.getPosition())
                        .build())
                .build();
    }

    @Override
    @Cacheable(value = "employeeCache", key = "#idRequest.employeeId")

    public EmployeeResponse employeeById(IdRequest idRequest) {

        boolean isEmployeeExist = employeeRepository.existsById(idRequest.getEmployeeId());
        {
            if (!isEmployeeExist) {
                return EmployeeResponse.builder()
                        .responseCode(EmployeeUtills.Employee_NotExisted_CODE)
                        .responseMessage(EmployeeUtills.Employee_NotExisted_MESSAGE)
                        .employeeInfo(null)

                        .build();
            }

            EmployeeDetails findEmployee = employeeRepository.findById(idRequest.getEmployeeId()).orElse(null);
            return EmployeeResponse.builder()
                    .responseCode(EmployeeUtills.Employee_EXIST_CODE)
                    .responseMessage(EmployeeUtills.Employee_EXIST_MESSAGE)
                    .employeeInfo(EmployeeInfo.builder()
                            .employeeName(findEmployee.getEmployeeName())
                            .position(findEmployee.getPosition())
                            .gender(findEmployee.getGender())
                            .department(findEmployee.getDepartment())
                            .salary(findEmployee.getSalary())
                            .dateOfJoining(findEmployee.getDateOfJoining())
                            .build())
                    .build();
        }
    }

    @Override
    public EmployeeResponse updateEmployee(EmployeeUpdateRequest employeeUpdateRequest) {
        EmployeeDetails existingEmployee = getExistingEmployee(employeeUpdateRequest.getEmployeeId());

        if (existingEmployee==null) {
           return EmployeeResponse.builder()
                    .responseCode(EmployeeUtills.Employee_NotExisted_CODE)
                    .responseMessage(EmployeeUtills.Employee_NotExisted_MESSAGE)
                    .employeeInfo(null)
                    .build();
        }

        EmployeeDetails updatedEmployee = EmployeeDetails.builder()
                .employeeId(existingEmployee.getEmployeeId()) // Preserve ID
                .employeeName(employeeUpdateRequest.getEmployeeName() != null ? employeeUpdateRequest.getEmployeeName() : existingEmployee.getEmployeeName())
                .department(employeeUpdateRequest.getDepartment() != null ? employeeUpdateRequest.getDepartment() : existingEmployee.getDepartment())
                .position(employeeUpdateRequest.getPosition() != null ? employeeUpdateRequest.getPosition() : existingEmployee.getPosition())
                .salary(employeeUpdateRequest.getSalary() != null ? BigDecimal.valueOf(employeeUpdateRequest.getSalary()) : existingEmployee.getSalary())
                .gender(employeeUpdateRequest.getGender() != null ? employeeUpdateRequest.getGender() : existingEmployee.getGender())
                .dateOfJoining(employeeUpdateRequest.getDateOfJoining() != null ? LocalDate.parse(employeeUpdateRequest.getDateOfJoining()) : existingEmployee.getDateOfJoining())
                .build();

        employeeRepository.save(updatedEmployee);
        EmployeeInfo employeeInfo = EmployeeInfo.builder()
                .employeeName(updatedEmployee.getEmployeeName())
                .gender(updatedEmployee.getGender())
                .position(updatedEmployee.getPosition())
                .department(updatedEmployee.getDepartment())
                .salary(updatedEmployee.getSalary())
                .dateOfJoining(updatedEmployee.getDateOfJoining())
                .build();
        return EmployeeResponse.builder()
                .responseCode(EmployeeUtills.Employee_Updated_CODE)
                .responseMessage(EmployeeUtills.Employee_Updated_MESSAGE)
                .employeeInfo(employeeInfo)
                .build();
    }


    private EmployeeDetails getExistingEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }


    @Override
    public ResponseEntity<String> deleteById(Long employeeId) {
        Optional<EmployeeDetails> existingEmployee = employeeRepository.findById(employeeId);

        if (existingEmployee.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(EmployeeUtills.Employee_NotExisted_MESSAGE);
        }

        employeeRepository.deleteById(employeeId);
        return ResponseEntity.ok(EmployeeUtills.Employee_Deleted_MESSAGE);
    }
}





