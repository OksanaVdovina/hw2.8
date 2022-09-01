package skypro.hw28.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skypro.hw28.Employee;
import skypro.hw28.service.EmployeeService;

import java.util.List;

@RestController
    @RequestMapping("/employee")
    public class EmployeeController {

        private final EmployeeService employeeService;

        public EmployeeController(EmployeeService employeeService) {
            this.employeeService = employeeService;
        }

        @GetMapping("/add")
        public Employee add(@RequestParam("firstName") String firstName,
                            @RequestParam("lastName") String lastName,
                            @RequestParam("salary") double salary,
                            @RequestParam("departmentId") int department) {
            return employeeService.addEmployee(firstName, lastName, salary, department);
        }

        @GetMapping("/find")
        public Employee find(@RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName) {
            return employeeService.findEmployee(firstName, lastName);
        }

        @GetMapping("/remove")
        public Employee delete(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName) {
            return employeeService.removeEmployee(firstName, lastName);
        }

        @GetMapping
        public List<Employee> getAll() {
            return employeeService.getAll();
        }

}

