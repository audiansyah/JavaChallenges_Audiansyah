package FinalChallenge_JavaOOP.allowance;

import java.time.LocalDate;

public class Allowance {
    private LocalDate totalAllowance;

    public Allowance(LocalDate totalAllowance) {
        this.totalAllowance = totalAllowance;
    }

    public LocalDate getTotalAllowance() {
        return totalAllowance;
    }

    public void setTotalAllowance(LocalDate totalAllowance) {
        this.totalAllowance = totalAllowance;
    }
}
