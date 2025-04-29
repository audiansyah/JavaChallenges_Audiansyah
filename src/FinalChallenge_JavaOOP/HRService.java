package FinalChallenge_JavaOOP;

import FinalChallenge_JavaOOP.hr.Contract;
import FinalChallenge_JavaOOP.hr.Employee;
import FinalChallenge_JavaOOP.hr.Permanent;
import FinalChallenge_JavaOOP.hr.Trainee;

import java.util.List;

public interface HRService {
    List<Employee> initListEmployee();

    void displayEmployees(List<Permanent>employees, List<Contract>employeess, List<Trainee>employeesss);

}
