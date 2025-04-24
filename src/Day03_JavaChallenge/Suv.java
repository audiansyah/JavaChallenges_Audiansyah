package Day03_JavaChallenge;


import java.time.LocalDate;

public class Suv extends Car {
    private double rent;
    private double driver;

    public Suv(String noPolice, int year, double price, double tax, int seat, LocalDate transactionDate, double rent, double driver) {
        super(noPolice, VehicleType.SUV, year, price, tax, seat, transactionDate);
        this.rent = rent;
        this.driver = driver;
        setTotal(rent+driver);

    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
        super.setTotal(rent+driver);
    }

    public double getDriver() {
        return driver;
    }

    public void setDriver(double driver) {
        this.driver = driver;
        super.setTotal(rent+driver);
    }

    @Override
    public String toString() {
        return super.toString() +
                "rent=" + rent +
                ", driver=" + driver +
                '}';
    }
}
