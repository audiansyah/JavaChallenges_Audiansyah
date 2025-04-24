package Day03_JavaOOP.part04;

import java.time.LocalDate;

public class Programmer extends Employee {
    private double transportasi;

    public Programmer(String fullName, LocalDate hireDate, double salary, double transportasi) {
        super(fullName, hireDate, Roles.PROGRAMMER, salary);
        this.transportasi = transportasi;
        setTotalSalary(salary+transportasi);
    }

    public double getTransportasi() {
        return transportasi;
    }

    public void setTransportasi(double transportasi) {
        this.transportasi = transportasi;
        super.setTotalSalary(this.getSalary()+transportasi);
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
                ", transportasi= "+transportasi;
    }


    /* contoh : overloading, ga dipake*/
    public double getTunjangan(double transportasi){
        return this.transportasi;
    }

    public double getTunjangan(double transportasi,LocalDate hireDate){
        return transportasi;
    }
}