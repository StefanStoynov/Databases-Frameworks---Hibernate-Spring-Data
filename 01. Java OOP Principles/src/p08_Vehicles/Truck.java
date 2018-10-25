package p08_Vehicles;

import java.text.DecimalFormat;

public class Truck implements Vehicle {
    private double fuelQuantity;
    private double consumtion;

    public Truck(double fuelQuantity, double consumtion) {
        this.fuelQuantity = fuelQuantity;
        this.consumtion = consumtion + 1.6;
    }

    @Override
    public void driving(double distance) {
        if (fuelQuantity < distance * consumtion) {
            System.out.println("Truck needs refueling");
        } else {
            DecimalFormat df = new DecimalFormat("0.##");
            System.out.printf("Truck travelled %s km%n", df.format(distance));
            this.fuelQuantity -= distance * consumtion;
        }
    }

    @Override
    public void refueling(double liters) {
        if (liters < 0) {
            throw new IllegalArgumentException("invalid fuel");
        }
        this.fuelQuantity += liters * 0.95;
    }

    public double getFuelQuantity() {
        return this.fuelQuantity;
    }
}
