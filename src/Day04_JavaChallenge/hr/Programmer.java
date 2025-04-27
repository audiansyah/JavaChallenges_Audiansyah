package Day04_JavaChallenge.hr;

import Day04_JavaChallenge.hr.Roles;
import Day04_JavaChallenge.ISalary;
import Day04_JavaChallenge.salary.Overtime;
import Day04_JavaChallenge.salary.Transportasi;

import java.time.LocalDate;

public class Programmer extends  Employee implements ISalary {

    private Transportasi transport;
    private Overtime makan;

    public Programmer(int empId, String fullName, LocalDate hireDate, Roles role, double salary,
                      Transportasi transport, Overtime makan) {
        super(empId, fullName, hireDate, role, salary);
        this.transport = transport;
        this.makan = makan;
    }

    @Override
    public void calculateTotalSalary() {
        setTotalSalary((getSalary()+ transport.getBensin()+ transport.getSpj()+ transport.getTransportasi()+ makan.getMakan())- getTotalTax());
    }

    @Override
    public void calculateTax() {
        setTotalTax(getSalary() * 0.01);
    }
}
