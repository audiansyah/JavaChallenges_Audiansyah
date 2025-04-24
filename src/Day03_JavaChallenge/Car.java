package Day03_JavaChallenge;

import java.time.LocalDate;

public class Car extends VehiclePurchase {
    public Car(String noPolice, VehicleType vehicletype, int year, double price, double tax, int seat,
               LocalDate transactionDate) {
        super(noPolice, vehicletype, year, price, tax, seat, transactionDate);
        totalCar++;
    }

}
