package Day03_JavaOOP.part04;

import java.time.LocalDate;
import java.util.Random;

//1. implementasikan konsep encapsulation
public class Employee {
    //attribute instance
    private int empId;
    private String fullName;
    private LocalDate hireDate;
    private Roles role;
    private double salary;
    private double totalSalary;
    public static int totalEmployee;

    //empty constructor
    public Employee() {
        totalEmployee++;
    }

    // create constructor #1
    public Employee(int empId, String fullName, LocalDate hireDate, Roles role, double salary) {
        this.empId = empId;
        this.fullName = fullName;
        this.hireDate = hireDate;
        this.role = role;
        this.salary = salary;
        totalEmployee++;
    }
    // overloading constructor
    public Employee(String fullName, LocalDate hireDate, Roles role, double salary) {
        Random random = new Random();
        this.empId = random.nextInt(102,111);
        this.fullName = fullName;
        this.hireDate = hireDate;
        this.role = role;
        this.salary = salary;
        totalEmployee++;
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
        if (salary > 10_000_000){
            throw new IllegalArgumentException("Salary > 10jt");
        }
        this.salary = salary;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", fullName='" + fullName + '\'' +
                ", hireDate=" + hireDate +
                ", role='" + role + '\'' +
                ", salary=" + salary +
                ", totalSalary=" + totalSalary +
                '}';
    }
}
