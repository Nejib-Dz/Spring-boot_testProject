package com.najib.Spring_boot_testing.service;

import com.najib.Spring_boot_testing.exception.RessourseNotFoundException;
import com.najib.Spring_boot_testing.model.Employee;
import com.najib.Spring_boot_testing.repository.EmployeeRepository;
import com.najib.Spring_boot_testing.service.ServiceInterface.ServiceImpl.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;
   private Employee employee;
    @BeforeEach
    public void setup(){

//        employeeRepository = Mockito.mock(EmployeeRepository.class);
//        employeeService = new EmployeeServiceImpl(employeeRepository);
        employee = Employee.builder()
                .id(1l)
                .lastName("dziri")
                .firstName("najib")
                .email("najib@gmail.com")
                .build();
    }

     // JUnit test for save employee method
        @DisplayName("JUnit test for save employee method")
         @Test
         public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){

             // given - precondation or setup

             given(employeeRepository.findByEmail(employee.getEmail()))
                     .willReturn(Optional.empty());

             given(employeeRepository.save(employee)).willReturn(employee);

           System.out.println(employeeRepository);
            System.out.println(employeeRepository);

             //when - action or the behaviour that we are going test
             Employee savedEmployee = employeeService.saveEmployee(employee);
            System.out.println(savedEmployee);

             // when - verify the output
            assertThat(savedEmployee).isNotNull();
         }
    // JUnit test for save employee method
    @DisplayName("JUnit test for save employee method with throws exception")
    @Test
    public void givenEmailException_whenSaveEmployee_thenThrowsException(){

        // given - precondation or setup

        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));

        //given(employeeRepository.save(employee)).willReturn(employee);

        System.out.println(employeeRepository);
        System.out.println(employeeRepository);

        //when - action or the behaviour that we are going test
        Assertions.assertThrows(RessourseNotFoundException.class, () ->{
            employeeService.saveEmployee(employee);
        });

        // when - verify the output
       verify(employeeRepository, never()).save(any(Employee.class));
    }

       // JUnit test for getAll Employee list
        @DisplayName("JUnit test for getAll Employee list")
         @Test
         public void givenListEmployee_whenGetAllEmployee_thenEmployeeList(){

             // given - precondation or setup
             Employee employee1 = Employee.builder()
                     .id(2l)
                     .email("mark@gamil.com")
                     .firstName("mark")
                     .lastName("toto")
                     .build();
             given(employeeRepository.findAll()).willReturn(List.of(employee1, employee));

             //when - action or the behaviour that we are going test
             List<Employee> employeeList = employeeService.getAllEmployee();

             // when - verify the output
             assertThat(employeeList).isNotNull();
             assertThat(employeeList.size()).isEqualTo(2);
         }
    // JUnit test for getAll Employee list
    @DisplayName("JUnit test for getAll Employee list (negative scenario)")
    @Test
    public void givenEmptyListEmployee_whenGetAllEmployee_thenEmptyEmployeeList(){

        // given - precondation or setup
        Employee employee1 = Employee.builder()
                .id(2l)
                .email("mark@gamil.com")
                .firstName("mark")
                .lastName("toto")
                .build();
        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        //when - action or the behaviour that we are going test
        List<Employee> employeeList = employeeService.getAllEmployee();

        // when - verify the output
        assertThat(employeeList).isEmpty();
        assertThat(employeeList.size()).isEqualTo(0);
    }

        // JUnit test for getEmployee ById
        @DisplayName("JUnit test for getEmployee ById ")
         @Test
         public void givenEmployeeById_whenGetEmployeeById_thenReturnEmployeeObject(){

             // given - precondation or setup
             given(employeeRepository.findById(1l)).willReturn(Optional.of(employee));

             //when - action or the behaviour that we are going test
             Employee saveEmployee = employeeService.getEmployeeById(employee.getId()).get();

             // when - verify the output

             assertThat(saveEmployee).isNotNull();
         }

          // JUnit test for updateEmployee methode
             @DisplayName("JUnit test for updateEmployee methode")
              @Test
              public void givenEmployeeObject_whenUpdateEmployee_thenReturnEmployeeObject(){

                  // given - precondation or setup
                  given(employeeRepository.save(employee)).willReturn(employee);
                  employee.setEmail("ali@gmail.com");
                  employee.setFirstName("ali");

                  //when - action or the behaviour that we are going test
                 Employee updateEmployee = employeeService.updateEmpl(employee);

                  // when - verify the output
                  assertThat(updateEmployee.getEmail()).isEqualTo("ali@gmail.com");
                  assertThat(updateEmployee.getFirstName()).isEqualTo("ali");
              }
    // JUnit test for updateEmployee methode
    @DisplayName("JUnit test for deleteEmployee methode")
    @Test
    public void givenEmployeeObject_whenDeleteEmployeeById_thenNoThing(){

        // given - precondation or setup
        Long employeeId = 1L;
        willDoNothing().given(employeeRepository).deleteById(employeeId);


        //when - action or the behaviour that we are going test
          employeeService.deleteEmpl(employeeId);

        // when - verify the output
       verify(employeeRepository, times(1)).deleteById(employeeId);
    }
}
