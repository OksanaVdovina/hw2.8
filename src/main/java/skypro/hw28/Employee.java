package skypro.hw28;

import java.util.Objects;

    public class Employee {
        private final String firstName;
        private final String lastName;

        private double salary;
        private int department;


        public Employee(String firstName, String lastName, double salary, int department){
            this.firstName = firstName;
            this.lastName = lastName;
            this.salary = salary;
            this.department = department;

        }


        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public double getSalary() {
            return salary;
        }

        public int getDepartment() {
            return department;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public void setDepartment(int department) {
            this.department = department;
        }

        @Override
        public String toString() {
            return "Фамилия сотрудника: " + firstName + ", Имя сотрудника " + lastName + ", Зарплата" + salary + ", отдел: " + department;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Employee)) return false;
            Employee employee = (Employee) o;
            return salary == employee.salary && department == employee.department && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName, salary, department);
        }

    }

