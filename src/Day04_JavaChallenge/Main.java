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
        empInf.generateTax(employees);

        empInf.generateSalary(employees);

        empInf.displayEmployees(employees);

//        List<Programmer> programmers = employees.stream()
//                .filter(Programmer.class::isInstance)
//                .map(Programmer.class::cast)
//                .collect(Collectors.toList());
//
//
//        //generate salary
//        empInf.generateSalary(programmers);
//
//        empInf.displayEmployees(programmers);
    }
}
