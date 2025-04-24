package Day03_JavaOOP.part04;

import java.time.LocalDate;

public class Sales extends Employee{
    private double bonus;
    private double commisions;

    public Sales(String fullName, LocalDate hireDate, double salary, double bonus, double commisions) {
        super(fullName, hireDate, Roles.SALES, salary);
        this.bonus = bonus;
        this.commisions = commisions;
        setTotalSalary(salary+bonus+commisions);
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
        super.setTotalSalary(this.getSalary()+bonus+commisions);
    }

    public double getCommisions() {
        return commisions;
    }

    public void setCommisions(double commisions) {
        this.commisions = commisions;
        super.setTotalSalary(this.getSalary()+bonus+commisions);
    }

    /* contoh : overloading, ga dipake*/
    public double getTunjangan(double bonus){
        return this.bonus;
    }

    public double getTunjangan(double bonus,LocalDate hireDate){
        return bonus;
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
                ", bonus= "+bonus+", Commisions= "+commisions;
    }
}
