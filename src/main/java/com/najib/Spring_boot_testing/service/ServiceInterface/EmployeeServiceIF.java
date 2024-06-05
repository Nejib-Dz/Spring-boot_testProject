package com.najib.Spring_boot_testing.service.ServiceInterface;

import com.najib.Spring_boot_testing.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeServiceIF {

    Employee  saveEmployee(Employee employee);
    List<Employee> getAllEmployee();
    Employee updateEmpl(Employee updateEmployee);
    Optional<Employee> getEmployeeById(Long id);

    public void deleteEmpl(Long id);
}
