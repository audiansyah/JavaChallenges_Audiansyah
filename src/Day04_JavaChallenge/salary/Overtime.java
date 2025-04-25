package Day04_JavaChallenge.salary;

import java.time.LocalDate;

public class Overtime extends Salary{
    private static double makan;

    public Overtime(LocalDate payDay, double makan) {
        super(payDay);
        this.makan = makan;
    }

    public static double getMakan() {
        return makan;
    }

    public void setMakan(double makan) {
        this.makan = makan;
    }
}
