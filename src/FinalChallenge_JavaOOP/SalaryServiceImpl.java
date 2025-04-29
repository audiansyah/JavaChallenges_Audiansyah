package FinalChallenge_JavaOOP;

import FinalChallenge_JavaOOP.hr.Contract;
import FinalChallenge_JavaOOP.hr.Permanent;
import FinalChallenge_JavaOOP.hr.Trainee;

import java.util.List;

public class SalaryServiceImpl implements SalaryService {
    public void generateSalary(List<Permanent> employees, List<Contract> contracts) {
        for (Permanent emp : employees) {
            emp.calculateTotalInsurance();
        }
        for (Contract emp : contracts) {
            emp.calculateTotalInsurance();
        }
    }

    @Override
    public void generateOvertime(List<Permanent> employees, List<Contract> contracts) {
        for (Permanent emp : employees) {
            emp.calculateOvertime();
        }
        for (Contract emp : contracts) {
            emp.calculateOvertime();
        }
    }

    @Override
    public void generateOperational(List<Permanent> employees, List<Contract> contracts) {
        for (Permanent emp : employees) {
            emp.calculateOperational();
        }
        for (Contract emp : contracts) {
            emp.calculateOperational();
        }
    }

    @Override
    public void generateAllowance(List<Permanent> employees, List<Contract> contracts,List<Trainee> interns) {
        for (Permanent emp : employees) {
            emp.calculateAllowance();
        }
        for (Contract emp : contracts) {
            emp.calculateAllowance();
        }
        for (Trainee emp : interns) {
            emp.calculateAllowance();
        }
    }

    @Override
    public void generateTax(List<Permanent> employees, List<Contract> contracts) {
        for (Permanent emp : employees) {
            emp.calculateTax();
        }
        for (Contract emp : contracts) {
            emp.calculateTax();
        }
    }

    @Override
    public void generateTotalSalary(List<Permanent> employees, List<Contract> contracts, List<Trainee> interns) {
        for (Permanent emp : employees) {
            emp.calculateTotalSalary();
        }
        for (Contract emp : contracts) {
            emp.calculateTotalSalary();
        }
        for (Trainee emp : interns) {
            emp.calculateTotalSalary();
        }
    }
}
