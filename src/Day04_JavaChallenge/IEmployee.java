package Day04_JavaChallenge;

import Day04_JavaChallenge.hr.Employee;
import Day04_JavaChallenge.hr.Programmer;
import Day04_JavaChallenge.hr.Sales;

import java.util.List;

public interface IEmployee {
    List<Employee> initListEmployee();

    void displayEmployees(List<Employee> employees);

    void generateSalary(List<Employee> employees);

    void generateTax(List<Employee> employees);

//    void displayEmployees(List<Programmer> employees);
//
//    void generateSalary(List<Programmer> employees);

}
