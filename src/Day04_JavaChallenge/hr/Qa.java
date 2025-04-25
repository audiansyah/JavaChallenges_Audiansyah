package Day04_JavaChallenge.hr;

import Day04_JavaChallenge.hr.Roles;
import Day04_JavaChallenge.ISalary;
import Day04_JavaChallenge.salary.Overtime;

import java.time.LocalDate;

public class Qa extends Employee implements ISalary {
    private Overtime makan;

    public Qa(int empId, String fullName, LocalDate hireDate, Roles role, double salary, Overtime makan) {
        super(empId, fullName, hireDate, role, salary);
        this.makan = makan;
    }

    @Override
    public void calculateTotalSalary() {
        setTotalSalary(getSalary()+ makan.getMakan());
    }
}
