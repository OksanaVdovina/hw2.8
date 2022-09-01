package skypro.hw28.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import skypro.hw28.Employee;
import skypro.hw28.exceptions.EmployeeNotFoundException;

import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void beforeEach() {
        List<Employee> employees = List.of(
                new Employee("Василий", "Васильев", 55_000, 1),
                new Employee("Андрей", "Андреев", 75_000, 2),
                new Employee("Иван", "Иванов", 43_000, 3),
                new Employee("Мария", "Иванова", 54_000, 1),
                new Employee("Ирина", "Андреева", 56_000, 2)
        );
        when(employeeService.getAll()).thenReturn(employees);
    }



    @ParameterizedTest
    @MethodSource("employeeWithMaxSalaryParams")
    public void findEmployeeWithMaxSalaryFromDepartmentPositiveTest(int departmentId, Employee expected) {
        Assertions.assertThat(departmentService.findEmployeeWithMaxSalaryFromDepartment(departmentId)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("employeeWithMaxSalaryParams")
    public void findEmployeeWithMaxSalaryFromDepartmentNegativeTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->departmentService.findEmployeeWithMaxSalaryFromDepartment(4));
    }

    @ParameterizedTest
    @MethodSource("employeeWithMinSalaryParams")
    public void findEmployeeWithMinSalaryFromDepartmentPositiveTest(int departmentId, Employee expected) {
        Assertions.assertThat(departmentService.findEmployeeWithMinSalaryFromDepartment(departmentId)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("employeeWithMinSalaryParams")
    public void findEmployeeWithMinSalaryFromDepartmentNegativeTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->departmentService.findEmployeeWithMinSalaryFromDepartment(4));
    }

    @ParameterizedTest
    @MethodSource("findEmployeesFromDepartmentParams")
    public void findEmployeesFromDepartmentPositiveTest(int departmentId, List<Employee> expected) {
        Assertions.assertThat(departmentService.findEmployeesFromDepartment(departmentId)).containsExactlyElementsOf(expected);
    }

    @ParameterizedTest
    @MethodSource("findEmployeesFromDepartmentParams")
    public void findEmployeesFromDepartmentNegativeTest() {
        Assertions.assertThat(departmentService.findEmployeesFromDepartment(4)).isEmpty();
    }


    public static Stream<Arguments> employeeWithMaxSalaryParams() {
        return Stream.of(
                Arguments.of(2,new Employee("Андрей", "Андреев", 75_000, 2)),
                Arguments.of(3, new Employee("Иван", "Иванов", 43_000, 3))
        );
    }

    public static Stream<Arguments> employeeWithMinSalaryParams() {
        return Stream.of(
                Arguments.of(2,new Employee("Ирина", "Андреева", 56_000, 2)),
                Arguments.of(3, new Employee("Иван", "Иванов", 43_000, 3))
        );
    }

    public static Stream<Arguments> findEmployeesFromDepartmentParams() {
        return Stream.of(
                Arguments.of(1,List.of(new Employee("Василий", "Васильев", 55_000, 1), new Employee("Мария", "Иванова", 54_000, 1))),
                Arguments.of(3, List.of(new Employee("Иван", "Иванов", 43_000, 3)))
        );
    }

}
