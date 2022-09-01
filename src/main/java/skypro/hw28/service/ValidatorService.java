package skypro.hw28.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import skypro.hw28.Employee;
import skypro.hw28.exceptions.InvalidFirstNameException;
import skypro.hw28.exceptions.InvalidLastNameException;

@Service
public class ValidatorService {

    public Employee validateEmployee (String firstName,
                                      String lastName,
                                      double salary,
                                      int department) {

        if (!StringUtils.isAlpha(firstName)) {
            throw new InvalidFirstNameException();
        }
        if (!StringUtils.isAlpha(lastName)) {
            throw new InvalidLastNameException();
        }
        return new Employee(
                StringUtils.capitalize(firstName.toLowerCase()),
                StringUtils.capitalize(lastName.toLowerCase()),
                salary,
                department);

    }
}
