package p08_Vehicles;

import java.text.DecimalFormat;

public class Car implements Vehicle {
    private double fuelQuantity;
    private double consumtion;

    public Car(double fuelQuantity, double consumtion) {
        this.fuelQuantity = fuelQuantity;
        this.consumtion = consumtion + 0.9;
    }

    @Override
    public void driving(double distance) {
        if (fuelQuantity < consumtion * distance) {
            System.out.println("Car needs refueling");
        } else {
            DecimalFormat df = new DecimalFormat("0.##");
            System.out.printf("Car travelled %s km%n", df.format(distance));
            this.fuelQuantity -= consumtion * distance;
        }
    }

    @Override
    public void refueling(double liters) {
        this.fuelQuantity += liters;
    }

    public double getFuelQuantity() {
        return this.fuelQuantity;
    }
}
