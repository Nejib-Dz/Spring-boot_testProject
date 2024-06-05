package com.najib.Spring_boot_testing.service.ServiceInterface.ServiceImpl;

import com.najib.Spring_boot_testing.exception.RessourseNotFoundException;
import com.najib.Spring_boot_testing.model.Employee;
import com.najib.Spring_boot_testing.repository.EmployeeRepository;
import com.najib.Spring_boot_testing.service.ServiceInterface.EmployeeServiceIF;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeServiceIF {


    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {

        Optional<Employee> saveemployee = employeeRepository.findByEmail(employee.getEmail());
        if (saveemployee.isPresent()){
            throw new RessourseNotFoundException("Employee already exist with given email: "+ employee.getEmail());
        }


        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee(){
      return employeeRepository.findAll();
    }



    @Override
    public Optional<Employee> getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmpl(Employee updateEmployee){
        return employeeRepository.save(updateEmployee);
    }

    @Override
    public void deleteEmpl(Long id){
         employeeRepository.deleteById(id);
    }
}
