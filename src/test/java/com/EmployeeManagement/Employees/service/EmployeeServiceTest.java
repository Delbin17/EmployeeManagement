package com.EmployeeManagement.Employees.service;

import com.EmployeeManagement.Employees.employeeDto.EmployeeRequest;
import com.EmployeeManagement.Employees.employeeDto.EmployeeResponse;
import com.EmployeeManagement.Employees.employeeEntity.EmployeeDetails;
import com.EmployeeManagement.Employees.employeeRepository.EmployeeRepository;
import com.EmployeeManagement.Employees.employeeService.EmployeeService;
import com.EmployeeManagement.Employees.employeeService.EmployeeServiceImpl;
import com.EmployeeManagement.Employees.employeeUtills.EmployeeUtills;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;


import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {



    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldNotAddEmployeeIfAlreadyExists() {
        EmployeeRequest employeeRequest = new EmployeeRequest(1L, "Delbin", BigDecimal.valueOf(50000), "Developer", "It", "male","1234");
        when(employeeRepository.existsByEmployeeName("Delbin")).thenReturn(true);

        EmployeeResponse response = employeeService.addEmployee(employeeRequest);

        assertEquals(EmployeeUtills.Employee_EXIST_CODE, response.getResponseCode());
        assertEquals(EmployeeUtills.Employee_EXIST_MESSAGE, response.getResponseMessage());
        assertNull(response.getEmployeeInfo());
        verify(employeeRepository, never()).save(any(EmployeeDetails.class));

    }
    @Test
    void shouldAddNewEmployee() {
        EmployeeRequest employeeRequest = new EmployeeRequest(1L, "Delbin", BigDecimal.valueOf(50000), "Developer", "It", "male","1234");
        EmployeeDetails employeeDetails = EmployeeDetails.builder()
                .employeeId(1L)
                .employeeName("Delbin")
                .department("IT")
                .position("Developer")
                .salary(BigDecimal.valueOf(50000))
                .gender("male")
                .build();

        when(employeeRepository.existsByEmployeeName("Delbin")).thenReturn(false);
        when(employeeRepository.save(any(EmployeeDetails.class))).thenReturn(employeeDetails);

        EmployeeResponse response = employeeService.addEmployee(employeeRequest);

        assertEquals(EmployeeUtills.Employee_Created_CODE, response.getResponseCode());
        assertEquals(EmployeeUtills.Employee_Created_MESSAGE, response.getResponseMessage());
        assertNotNull(response.getEmployeeInfo());
        assertEquals("Delbin", response.getEmployeeInfo().getEmployeeName());

        verify(employeeRepository, times(1)).save(any(EmployeeDetails.class));
    }






}