package FinalChallenge_JavaOOP.hr;

public abstract class Employee {
    private int empNo;
    private String fullName;
    private EmployeeType status;
    private double salary;
    private double totalInsurance;
    private double totalOvertime;
    private double totalOperational;
    private double totalTax;
    private int totalSalary;
    private double totalAllowance;

    public Employee(int empNo, String fullName, EmployeeType status, double salary){
        this.empNo = empNo;
        this.fullName = fullName;
        this.status = status;
        this.salary = salary;
    }

    public double getTotalInsurance() {
        return totalInsurance;
    }

    public void setTotalInsurance(double totalInsurance) {
        this.totalInsurance = totalInsurance;
    }

    public double getTotalOvertime() {
        return totalOvertime;
    }

    public void setTotalOvertime(double totalOvertime) {
        this.totalOvertime = totalOvertime;
    }

    public double getTotalOperational() {
        return totalOperational;
    }

    public void setTotalOperational(double totalOperational) {
        this.totalOperational = totalOperational;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }

    public int getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(int totalSalary) {
        this.totalSalary = totalSalary;
    }

    public double getTotalAllowance() {
        return totalAllowance;
    }

    public void setTotalAllowance(double totalAllowance) {
        this.totalAllowance = totalAllowance;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public EmployeeType getStatus() {
        return status;
    }

    public void setStatus(EmployeeType status) {
        this.status = status;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empNo +
                ", fullName='" + fullName + '\'' +
                ", status=" + status +
                ", salary=" + salary +
                ", totalInsurance=" + totalInsurance +
                ", totalOvertime=" + totalOvertime +
                ", totalOperational=" + totalOperational +
                ", totalTax=" + totalTax +
                ", totalSalary=" + totalSalary +
                ", totalAlowance=" + totalAllowance +
                '}';
    }
}
