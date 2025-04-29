package FinalChallenge_JavaOOP.allowance;

import java.time.LocalDate;

public class Overtime extends Allowance{
    private double hours;
    private double uangLembur;

    public Overtime(LocalDate totalAllowance, double hours, double uangLembur) {
        super(totalAllowance);
        this.hours = hours;
        this.uangLembur = uangLembur;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public double getUangLembur() {
        return uangLembur;
    }

    public void setUangLembur(double uangLembur) {
        this.uangLembur = uangLembur;
    }
}
