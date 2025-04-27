package Day04_JavaChallenge.hr;

import Day04_JavaChallenge.hr.Roles;

import java.time.LocalDate;

//1. implementasikan konsep encapsulation
public class Employee  {
    //attribute instance
    private int empId;
    private String fullName;
    private LocalDate hireDate;
    private Roles role;
    private double salary;
    private double totalSalary;
    private double totalTax;

    public Employee(int empId, String fullName, LocalDate hireDate, Roles role, double salary) {
        this.empId = empId;
        this.fullName = fullName;
        this.hireDate = hireDate;
        this.role = role;
        this.salary = salary;
    }

    public void calculateTotalSalary() {
        this.totalSalary = this.salary;
    }

    public void calculateTax(){this.totalTax = totalTax;}


    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", fullName='" + fullName + '\'' +
                ", hireDate=" + hireDate +
                ", role=" + role +
                ", salary=" + salary +
                ", totalSalary=" + totalSalary +
                ", totalTax=" + totalTax +
                '}';
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }
}
