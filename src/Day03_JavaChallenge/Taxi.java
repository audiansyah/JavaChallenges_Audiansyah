package Day03_JavaChallenge;


import java.time.LocalDate;

public class Taxi extends Car {
    private int order;
    private double orderPerKm;

    public Taxi(String noPolice, int year, double price, double tax, int seat, LocalDate transactionDate, int order, double orderPerKm) {
        super(noPolice, VehicleType.Taxi, year, price, tax, seat, transactionDate);
        this.order = order;
        this.orderPerKm = orderPerKm;
        super.setTotal(order * orderPerKm);
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
        super.setTotal(order * orderPerKm);
    }

    public double getOrderPerKm() {
        return orderPerKm;
    }

    public void setOrderPerKm(double orderPerKm) {
        this.orderPerKm = orderPerKm;
        super.setTotal(order * orderPerKm);
    }

    @Override
    public String toString() {
        return super.toString() +
                "order=" + order +
                ", orderPerKm=" + orderPerKm +
                '}';
    }
}
