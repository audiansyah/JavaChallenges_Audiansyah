package Day03_JavaChallenge;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        VehiclePurchase vhc1 = new Suv("D 1001 UM",2010,350_000_000, 3_500_000,
                4, LocalDate.of(2023,1,10),500_000,150_000);
        VehiclePurchase vhc2 = new Suv("D 1002 UM",2010,350_000_000, 3_500_000,
                4, LocalDate.of(2023,1,10),500_000,150_000);
        VehiclePurchase vhc3 = new Suv("D 1003 UM",2015,350_000_000, 3_500_000,
                5, LocalDate.of(2023,1,12),500_000,150_000);
        VehiclePurchase vhc4 = new Suv("D 1004 UM",2015,350_000_000, 3_500_000,
                5, LocalDate.of(2023,1,13),500_000,150_000);
        VehiclePurchase vhc5 = new Taxi("D 11 UK",2002,175_000_000, 1_750_000,
                4, LocalDate.of(2023,1,12),45,4_500);
        VehiclePurchase vhc6 = new Taxi("D 12 UK",2015,275_000_000, 2_250_000,
                4, LocalDate.of(2023,1,12),75,4_500);
        VehiclePurchase vhc7 = new Taxi("D 13 UK",2020,275_000_000, 2_750_000,
                4, LocalDate.of(2023,1,12),90,4_500);
        VehiclePurchase vhc8 = new Plane("ID8089", 2015,150E9,15E8,12,
                LocalDate.of(2022,12,23),55_000_000);
        VehiclePurchase vhc9 = new Plane("ID8099", 2018,175E9,175E7,15,
                LocalDate.of(2022,12,25),75_000_000);
        VehiclePurchase vhc10 = new Boat("Boat18", 2020,2E9,20_000_000,12,
                LocalDate.of(2022,12,25),10_000_000);


        List<VehiclePurchase> listVehiclePurchase= List.of(vhc1,vhc2,vhc3,vhc4,vhc5,vhc6,vhc7,vhc8,vhc9,vhc10);
        int TotalIncomePlane = 0;
        int TotalIncomeBoat = 0;
        int TotalIncomeCar = 0;
        long TotalIncomeTax = 0;
        for (VehiclePurchase vhc : listVehiclePurchase){
            TotalIncomeTax += vhc.getTax();
            if (vhc.getVehicletype() == VehicleType.PrivateJet){
                TotalIncomePlane += vhc.getTotal();
            } else if (vhc.getVehicletype() == VehicleType.Boat) {
                TotalIncomeBoat += vhc.getTotal();
            } else {
                TotalIncomeCar += vhc.getTotal();
            }
            System.out.println(vhc.toString());
        }

        // totalVehiclePurchase dimiliki oleh class, bukan object instance
        System.out.println("Total Income Car : " + TotalIncomeCar);
        System.out.println("Total Income Plane : " + TotalIncomePlane);
        System.out.println("Total Income Boat : " + TotalIncomeBoat);
        System.out.println("Total Tax : " + TotalIncomeTax);
        System.out.println("Total Car : "+ VehiclePurchase.totalCar);
        System.out.println("Total Plane : "+ VehiclePurchase.totalPlane);
        System.out.println("Total Boat : "+ VehiclePurchase.totalBoat);
    }

}
