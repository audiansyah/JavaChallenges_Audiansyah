package Day03_JavaOOP.part04;

import java.time.LocalDate;

public class Qa extends Employee{
    private double makan;

    public Qa(String fullName, LocalDate hireDate, double salary, double makan) {
        super(fullName, hireDate, Roles.QA, salary);
        this.makan = makan;
        setTotalSalary(salary+makan);
    }

    public double getMakan() {
        return makan;
    }

    public void setMakan(double makan) {
        this.makan = makan;
        super.setTotalSalary(this.getSalary()+makan);
    }

    /* contoh : overloading, ga dipake*/
    public double getTunjangan(double makan){
        return this.makan;
    }

    public double getTunjangan(double makan,LocalDate hireDate){
        return makan;
    }

    public LocalDate getTunjangan(LocalDate hireDate){
        return hireDate;
    }

    @Override
    public void setTotalSalary(double totalSalary) {
        super.setTotalSalary(totalSalary);
    }

    @Override
    public String toString() {
        return super.toString()+
                ", makan= "+makan;
    }
}
