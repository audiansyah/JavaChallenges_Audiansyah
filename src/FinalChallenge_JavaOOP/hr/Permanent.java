package FinalChallenge_JavaOOP.hr;

import FinalChallenge_JavaOOP.AllowanceService;
import FinalChallenge_JavaOOP.TaxSalary;
import FinalChallenge_JavaOOP.allowance.Insurance;
import FinalChallenge_JavaOOP.allowance.Operational;
import FinalChallenge_JavaOOP.allowance.Overtime;

public class Permanent extends Employee implements AllowanceService {
    private Insurance insurance;
    private Overtime overtime;
    private Operational operational;
    private TaxSalary taxSalary;

    public Permanent(int empNo, String fullName, double salary, Insurance insurance,
                     Overtime overtime, Operational operational, TaxSalary taxSalary){
        super(empNo, fullName, EmployeeType.PERMANENT, salary);
        this.insurance = insurance;
        this.overtime = overtime;
        this.operational = operational;
        this.taxSalary = taxSalary;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public Overtime getOvertime() {
        return overtime;
    }

    public void setOvertime(Overtime overtime) {
        this.overtime = overtime;
    }

    public Operational getOperational() {
        return operational;
    }

    public void setOperational(Operational operational) {
        this.operational = operational;
    }

    public TaxSalary getTaxSalary() {
        return taxSalary;
    }

    public void setTaxSalary(TaxSalary taxSalary) {
        this.taxSalary = taxSalary;
    }

    @Override
    public void calculateTotalInsurance() {
        setTotalInsurance((getSalary() * insurance.getMedical() / 100) * (insurance.getSelf() + insurance.getDependent()));
    }

    @Override
    public void calculateOvertime() {
        setTotalOvertime(overtime.getHours()*overtime.getUangLembur());
    }


    @Override
    public void calculateOperational() {
        setTotalOperational((operational.getLunch()+operational.getTransport())*operational.getDays());
    }

    @Override
    public void calculateTax() {
        setTotalTax(getSalary() * (taxSalary.getPPH() + taxSalary.getTapera()) / 100);
    }

    @Override
    public void calculateTotalSalary() {
        setTotalSalary((int) (getSalary() - getTotalInsurance() - getTotalTax() + (getTotalOvertime()+ getTotalOperational())));
    }

    @Override
    public void calculateAllowance() {
        setTotalAllowance(getTotalOperational() + getTotalOvertime());
    }
}
