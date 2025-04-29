package FinalChallenge_JavaOOP;


import java.util.List;
import java.util.stream.Collectors;

import FinalChallenge_JavaOOP.hr.*;

public class Main {
    public static void main(String[] args) {
        HRService empinf = new HRServiceImpl();
        var employees = empinf.initListEmployee();

        SalaryService salaryInf = new SalaryServiceImpl();
        var employeess = empinf.initListEmployee();


        List<Permanent> permanents = employees.stream()
                .filter(Permanent.class::isInstance)
                .map(Permanent.class::cast)
                .collect(Collectors.toList());

        List<Contract> contracts = employees.stream()
                .filter(Contract.class::isInstance)
                .map(Contract.class::cast)
                .collect(Collectors.toList());

        List<Trainee> trainees = employees.stream()
                .filter(Trainee.class::isInstance)
                .map(Trainee.class::cast)
                .collect(Collectors.toList());

        salaryInf.generateSalary(permanents,contracts);
        salaryInf.generateOvertime(permanents,contracts);
        salaryInf.generateOperational(permanents,contracts);
        salaryInf.generateTax(permanents,contracts);
        salaryInf.generateTotalSalary(permanents,contracts,trainees);
        salaryInf.generateAllowance(permanents,contracts,trainees);
        empinf.displayEmployees(permanents,contracts,trainees);
    }
}
