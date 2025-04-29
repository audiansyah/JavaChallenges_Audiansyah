package FinalChallenge_JavaOOP.allowance;

import java.time.LocalDate;

public class Insurance extends Allowance {
    private int dependent;
    private double medical;
    private double self;

    public Insurance(LocalDate totalAllowance, int dependent, double medical, double self) {
        super(totalAllowance);
        this.dependent = dependent;
        this.medical = medical;
        this.self = self;
    }

    public int getDependent() {
        return dependent;
    }

    public void setDependent(int dependent) {
        this.dependent = dependent;
    }

    public double getMedical() {
        return medical;
    }

    public void setMedical(double medical) {
        this.medical = medical;
    }

    public double getSelf() {
        return self;
    }

    public void setSelf(double self) {
        this.self = self;
    }
}
