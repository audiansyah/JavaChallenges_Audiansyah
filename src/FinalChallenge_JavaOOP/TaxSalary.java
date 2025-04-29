package FinalChallenge_JavaOOP;


import FinalChallenge_JavaOOP.allowance.Allowance;

import java.time.LocalDate;

public class TaxSalary extends Allowance {
    private double PPH;
    private double Tapera;

    public TaxSalary(LocalDate totalAllowance, double PPH, double tapera) {
        super(totalAllowance);
        this.PPH = PPH;
        Tapera = tapera;
    }

    public double getPPH() {
        return PPH;
    }

    public void setPPH(double PPH) {
        this.PPH = PPH;
    }

    public double getTapera() {
        return Tapera;
    }

    public void setTapera(double tapera) {
        Tapera = tapera;
    }
}
