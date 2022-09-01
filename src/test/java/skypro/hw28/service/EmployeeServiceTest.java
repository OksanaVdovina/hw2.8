package skypro.hw28.service;

import org.assertj.core.api.Assertions;
import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import skypro.hw28.Employee;
import skypro.hw28.exceptions.EmployeeAlreadyAddedException;
import skypro.hw28.exceptions.EmployeeNotFoundException;
import skypro.hw28.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeServiceTest {

    EmployeeService employeeService = new EmployeeService(new ValidatorService());

    @ParameterizedTest
    @MethodSource("params")
    void addEmployeeNegative1Test(String firstname,
                              String lastname,
                              double salary,
                              int department) {
        Employee expected = new Employee(firstname, lastname, salary, department);
        Assertions.assertThat(employeeService.addEmployee(firstname, lastname, salary, department)).isEqualTo(expected);

        Assertions.assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(()->employeeService.addEmployee(firstname, lastname, salary, department));
    }

    @ParameterizedTest
    @MethodSource("params")
    void addEmployeePositiveTest(String firstname,
                                  String lastname,
                                  double salary,
                                  int department) {
        Employee expected = new Employee(firstname, lastname, salary, department);
        Assertions.assertThat(employeeService.addEmployee(firstname, lastname, salary, department)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("params")
    void removeEmployeeNegativeTest(String firstname,
                                    String lastname,
                                    double salary,
                                    int department) {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeService.removeEmployee("test","test"));

        Employee expected = new Employee(firstname, lastname, salary, department);
        Assertions.assertThat(employeeService.addEmployee(firstname, lastname, salary, department)).isEqualTo(expected);
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeService.removeEmployee("test","test"));
    }


    @ParameterizedTest
    @MethodSource("params")
    void removeEmployeePositiveTest(String firstname,
                                    String lastname,
                                    double salary,
                                    int department) {
        Employee expected = new Employee(firstname, lastname, salary, department);
        Assertions.assertThat(employeeService.addEmployee(firstname, lastname, salary, department)).isEqualTo(expected);
        Assertions.assertThat(employeeService.removeEmployee(firstname, lastname)).isEqualTo(expected);
        Assertions.assertThat(employeeService.getAll()).isEmpty();
    }


    @ParameterizedTest
    @MethodSource("params")
    void findEmployeeNegativeTest(String firstname,
                                    String lastname,
                                    double salary,
                                    int department) {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeService.findEmployee("test","test"));

        Employee expected = new Employee(firstname, lastname, salary, department);
        Assertions.assertThat(employeeService.addEmployee(firstname, lastname, salary, department)).isEqualTo(expected);
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeService.findEmployee("test","test"));
    }


    @ParameterizedTest
    @MethodSource("params")
    void findEmployeePositiveTest(String firstname,
                                    String lastname,
                                    double salary,
                                    int department) {
        Employee expected = new Employee(firstname, lastname, salary, department);
        Assertions.assertThat(employeeService.addEmployee(firstname, lastname, salary, department)).isEqualTo(expected);
        Assertions.assertThat(employeeService.findEmployee(firstname, lastname)).isEqualTo(expected);
        Assertions.assertThat(employeeService.getAll()).hasSize(1);
    }



    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of("Ivan", "Ivanov", 55000, 1),
                Arguments.of("Petr", "Petrov", 65000, 2),
                Arguments.of("Maria", "Ivanova", 80000, 3)
        );
    }

}
