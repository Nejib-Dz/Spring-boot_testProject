package com.najib.Spring_boot_testing.repository;

import com.najib.Spring_boot_testing.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.Optional;


@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;
    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .firstName("najib")
                .lastName("dziri")
                .email("najib@gmail.com")
                .build();
    }

    //JUnit test for save emlpoyee operation
     @DisplayName("JUnit test for save emlpoyee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSaveEmployee(){

        //given - precondation or setup
       /* Employee employee = Employee.builder()
                .firstName("najib")
                .lastName("dziri")
                .email("najib@gmail.com")
                .build();*/

        //when - action or the behaviour that we are going test
        Employee saveEmployee = employeeRepository.save(employee);

        //when - verify the output
        Assertions.assertThat(saveEmployee).isNotNull();
        Assertions.assertThat(saveEmployee.getId()).isGreaterThan(0);
    }

    // JUnit test for get all employee operation
    @DisplayName("JUnit test for get all employee operation")
    @Test
    public void givenEmplyeeList_whenFindAll_thenEmployeeList(){

        // given - precondation or setup
      /*  Employee employee = Employee.builder()
                .firstName("najib")
                .lastName("dziri")
                .email("dziri@gmail.com")
                .build();*/
        Employee employee1 = Employee.builder()
                .firstName("ali")
                .lastName("ali")
                .email("ali@gmail.com")
                .build();
        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        //when - action or the behaviour that we are going test
        List<Employee> employeeList = employeeRepository.findAll();

    // when - verify the output
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList.size()).isEqualTo(2);
    }

     // JUnit test for emlpoyee By Id operation
         @DisplayName("JUnit test for get emlpoyee By Id operation")
         @Test
         public void givenObjectEmployee_whenEmployeeById_thenReturnEmployeeById(){

             // given - precondation or setup
           /*  Employee employee = Employee.builder()
                     .firstName("najib")
                     .lastName("dziri")
                     .email("dziri@gmail.com")
                     .build();*/
             employeeRepository.save(employee);

             //when - action or the behaviour that we are going test
             Employee employeeDb = employeeRepository.findById(employee.getId()).get();

             // when - verify the output
             Assertions.assertThat(employeeDb).isNotNull();
         }

          // JUnit test for get employee By email operation
            @DisplayName("JUnit test for get employee By email operation")
              @Test
              public void givenEmailEmployee_whenFindByEmail_thenReturnEmployeeForObject(){

                  // given - precondation or setup
                 /* Employee employee = Employee.builder()
                          .firstName("najib")
                          .lastName("ali")
                          .email("dziri@gmail.com")
                          .build();*/
                  employeeRepository.save(employee);

                  //when - action or the behaviour that we are going test
              Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

                  // when - verify the output
                  Assertions.assertThat(employeeDB).isNotNull();
              }

               // JUnit test for update employee operation
                   @DisplayName("JUnit test for update employee operation")
                   @Test
                   public void givenEmployeeObjet_whenUpdateEmlpoyee_thenReturnUpdateEmployee(){

                       // given - precondation or setup
                      /* Employee employee = Employee.builder()
                               .firstName("najib")
                               .lastName("ali")
                               .email("dziri@gmail.com")
                               .build();*/
                       employeeRepository.save(employee);

                       //when - action or the behaviour that we are going test
                       Employee employeeSave = employeeRepository.findById(employee.getId()).get();
                       employeeSave.setFirstName("mohamed");
                       employeeSave.setEmail("mohamed@gmail.com");
                       Employee employeeUpdate = employeeRepository.save(employeeSave);

                       // when - verify the output
                       Assertions.assertThat(employeeUpdate.getFirstName()).isEqualTo(("mohamed"));
                       Assertions.assertThat(employeeUpdate.getEmail()).isEqualTo(("mohamed@gmail.com"));
                   }


                    // JUnit test for delete employee operation
                        @DisplayName("JUnit test for delete employee operation")
                        @Test
                        public void givenEmployeeObject_whenDelete_thenRemoveEmployee(){

                            // given - precondation or setup
                           /* Employee employee = Employee.builder()
                                    .firstName("najib")
                                    .lastName("dziri")
                                    .email("dziri@gmail.com")
                                    .build();*/
                            employeeRepository.save(employee);

                            //when - action or the behaviour that we are going test
                           employeeRepository.deleteById(employee.getId());
                            Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

                            // when - verify the output
                           Assertions.assertThat(employeeOptional).isEmpty();
                        }

                         // JUnit test for custom query using JPQL with index
                            @DisplayName("JUnit test for custom query using JPQL with index")
                             @Test
                             public void givenFirstNameAndLastName_whenFindByJPQL_thenEmployeeObject(){

                                 // given - precondation or setup
                                /* Employee employee = Employee.builder()
                                         .firstName("najib")
                                         .lastName("ali")
                                         .email("dziri@gmail.com")
                                         .build();*/
                                 employeeRepository.save(employee);
                                 //when - action or the behaviour that we are going test
                                 Employee saveemployee = employeeRepository.findByJPQL(employee.getFirstName(), employee.getLastName());
                                 // when - verify the output

                                 Assertions.assertThat(saveemployee).isNotNull();
                             }

    // JUnit test for custom query using JPQL with Named params
    @DisplayName("JUnit test for custom query using JPQL with Named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenEmployeeObject(){

        // given - precondation or setup
      /*  Employee employee = Employee.builder()
                .firstName("najib")
                .lastName("ali")
                .email("dziri@gmail.com")
                .build();*/
        employeeRepository.save(employee);
        //when - action or the behaviour that we are going test
        Employee saveemployee = employeeRepository.findByJPQLNamedParams(employee.getFirstName(), employee.getLastName());
        // when - verify the output

        Assertions.assertThat(saveemployee).isNotNull();
    }

     // JUnit test for custom query using native SQL with index
         @DisplayName("JUnit test for custom query using native SQL with index")
         @Test
         public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject(){

             // given - precondation or setup
             /*Employee employee = Employee.builder()
                     .firstName("najib")
                     .lastName("ali")
                     .email("dziri@gmail.com")
                     .build();*/
             employeeRepository.save(employee);
             //when - action or the behaviour that we are going test
             Employee saveemployee = employeeRepository.findByNativeSQL(employee.getFirstName(),employee.getLastName());
             // when - verify the output
             Assertions.assertThat(saveemployee).isNotNull();
         }
    // JUnit test for custom query using native SQL with named params
    @DisplayName("JUnit test for custom query using native SQL with named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject(){

        // given - precondation or setup
       /* Employee employee = Employee.builder()
                .firstName("najib")
                .lastName("ali")
                .email("dziri@gmail.com")
                .build();*/
        employeeRepository.save(employee);
        //when - action or the behaviour that we are going test
        Employee saveemployee = employeeRepository.findByNativeSQLNamedParams(employee.getFirstName(),employee.getLastName());
        // when - verify the output
        Assertions.assertThat(saveemployee).isNotNull();
    }

}
