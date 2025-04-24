package Day03_JavaChallenge;

import java.time.LocalDate;

public class Boat extends VehiclePurchase {
    private double orderPerHours;

    public Boat(String noPolice, int year, double price, double tax, int seat, LocalDate transactionDate, double orderPerHours) {
        super(noPolice, VehicleType.Boat, year, price, tax, seat, transactionDate);
        this.orderPerHours = orderPerHours;
        totalBoat++;
        setTotal(orderPerHours);
    }

    public double getOrderPerHours() {
        return orderPerHours;
    }

    public void setOrderPerHours(double orderPerHours) {
        this.orderPerHours = orderPerHours;
        super.setTotal(orderPerHours);
    }

    @Override
    public String toString() {
        return super.toString() +
                "orderPerHours=" + orderPerHours +
                '}';
    }
}
