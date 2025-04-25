package Day04_JavaChallenge;

import Day04_JavaChallenge.hr.Employee;
import Day04_JavaChallenge.hr.Programmer;
import Day04_JavaChallenge.hr.Sales;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        IEmployee empInf = new EmployeeImpl();

        List<Employee> employees = empInf.initListEmployee();


        //generate salary
        empInf.generateSalary(employees);

        empInf.displayEmployees(employees);
    }
}
