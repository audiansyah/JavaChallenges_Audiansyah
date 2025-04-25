package Day04_JavaChallenge.salary;

import java.time.LocalDate;

public class Commission extends Salary{
    private double comision;
    private double bonus;

    public Commission(LocalDate payDay, double comision, double bonus) {
        super(payDay);
        this.comision = comision;
        this.bonus = bonus;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

}
