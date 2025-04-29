package FinalChallenge_JavaOOP;

import FinalChallenge_JavaOOP.hr.Contract;
import FinalChallenge_JavaOOP.hr.Permanent;
import FinalChallenge_JavaOOP.hr.Trainee;

import java.util.List;

public interface SalaryService {
    void generateSalary(List<Permanent> employees, List<Contract> contracts);

    void generateOvertime(List<Permanent> employees, List<Contract> contracts);

    void generateOperational(List<Permanent> permanents, List<Contract> contracts);

    void generateAllowance(List<Permanent> permanents, List<Contract> contracts,List<Trainee> trainees);

    void generateTax(List<Permanent> employees, List<Contract> contracts);

    void generateTotalSalary(
            List<Permanent> employees,
            List<Contract> contracts,
            List<Trainee> trainees
    );
}
