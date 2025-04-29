package FinalChallenge_JavaOOP.hr;

import FinalChallenge_JavaOOP.AllowanceService;
import FinalChallenge_JavaOOP.allowance.Operational;

public class Trainee extends Employee implements AllowanceService {
    private Operational operational;

    public Trainee(int empNo, String fullName, double salary, Operational operational){
        super(empNo, fullName, EmployeeType.TRAINEE, salary);
        this.operational = operational;
    }


    @Override
    public void calculateTotalInsurance() {

    }

    @Override
    public void calculateOvertime() {

    }

    @Override
    public void calculateOperational() {
    }

    @Override
    public void calculateTax() {

    }

    @Override
    public void calculateAllowance() {
        setTotalAllowance((int) (operational.getDays()*(operational.getLunch()+operational.getTransport())));
    }

    @Override
    public void calculateTotalSalary() {
        setTotalSalary((int) (operational.getDays()*(operational.getLunch()+operational.getTransport())));
    }
}
