package FinalChallenge_JavaOOP;


import FinalChallenge_JavaOOP.allowance.Allowance;

import java.time.LocalDate;

public class TaxSalary extends Allowance {
    private double pph;
    private double tapera;

    public TaxSalary(LocalDate totalAllowance, double pph, double tapera) {
        super(totalAllowance);
        this.pph = pph;
        this.tapera = tapera;
    }

    public double getPph() {
        return pph;
    }

    public void setPph(double pph) {
        this.pph = pph;
    }

    public double getTapera() {
        return tapera;
    }

    public void setTapera(double tapera) {
        this.tapera = tapera;
    }
}
