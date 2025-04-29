package FinalChallenge_JavaOOP;

import FinalChallenge_JavaOOP.allowance.Insurance;
import FinalChallenge_JavaOOP.allowance.Operational;
import FinalChallenge_JavaOOP.allowance.Overtime;
import FinalChallenge_JavaOOP.hr.*;

import java.time.LocalDate;
import java.util.List;

public class HRServiceImpl implements HRService {
    @Override
    public List<Employee> initListEmployee() {
        Permanent permanent1 = new Permanent(100,"Anton", 20_000_000,
                new Insurance(LocalDate.now(),2,2,1),
                new Overtime(LocalDate.now(),10,50_000),
                new Operational(LocalDate.now(),10,50_000, 30_000),
                new TaxSalary(LocalDate.now(),0.5,0.5)
        );

        Permanent permanent2 = new Permanent(101,"Budi",15_000_000,
                new Insurance(LocalDate.now(),3,2,1),
                new Overtime(LocalDate.now(),5,50_000),
                new Operational(LocalDate.now(),10,50_000, 30_000),
                new TaxSalary(LocalDate.now(),0.5,0.5)
        );

        Contract contract1 = new Contract(102,"Charlie",15_000_000,
                new Insurance(LocalDate.now(),0,2,1),
                new Overtime(LocalDate.now(),5,45_000),
                new Operational(LocalDate.now(),10,50_000, 30_000),
                new TaxSalary(LocalDate.now(),0.5,0.5)
        );

        Contract contract2 = new Contract(103,"Dian",10_000_000,
                new Insurance(LocalDate.now(),0,2,1),
                new Overtime(LocalDate.now(),6,45_000),
                new Operational(LocalDate.now(),10,50_000, 30_000),
                new TaxSalary(LocalDate.now(),0.5,0.5)
        );

        Trainee intern1 = new Trainee(104,"Gita",0,
                new Operational(LocalDate.now(),25,30_000,20_000)
        );

        return List.of(permanent1, permanent2, contract1, contract2, intern1);
    }

    @Override
    public void displayEmployees(List<Permanent> employees, List<Contract> contracts, List<Trainee> interns) {
        int totalEmployee = employees.size() + contracts.size() + interns.size();
        int totalInsurance = 0;
        int totalEndSalary = 0;
        int totalOvertime = 0;
        int totalOperational = 0;
        int totalTax = 0;

        for (Permanent emp : employees) {
            totalInsurance += emp.getTotalInsurance();
            totalOvertime += emp.getTotalOvertime();
            totalOperational += emp.getTotalOperational();
            totalEndSalary += emp.getTotalSalary();
            totalTax += emp.getTotalTax();
            System.out.println(emp);
        }

        for (Contract emp : contracts) {
            totalInsurance += emp.getTotalInsurance();
            totalOvertime += emp.getTotalOvertime();
            totalOperational += emp.getTotalOperational();
            totalEndSalary += emp.getTotalSalary();
            totalTax += emp.getTotalTax();
            System.out.println(emp);
        }

        for (Trainee emp : interns) {
            totalInsurance += emp.getTotalInsurance();
            totalOvertime += emp.getTotalOvertime();
            totalOperational += emp.getTotalOperational();
            totalEndSalary += emp.getTotalSalary();
            totalTax += emp.getTotalTax();
            System.out.println(emp);
        }

        System.out.println("\n------------------------------------------------ SUMMARY ------------------------------------------------");
        System.out.println("Total Employee     : " + totalEmployee);
        System.out.println("Total Salary       : Rp " + totalEndSalary);
        System.out.println("Total Insurance    : Rp " + totalInsurance);
        System.out.println("Total Overtime     : Rp " + totalOvertime);
        System.out.println("Total Operational  : Rp " + totalOperational);
        System.out.println("Total Tax          : Rp " + totalTax);
        System.out.println(
                "----------------------------------------------------------------------------------------------------------");
    }
}
