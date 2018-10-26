package oopPrinciples.p08_Vehicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[]carInfo = reader.readLine().split("\\s+");
        String[]truckInfo = reader.readLine().split("\\s+");

        int numberOfCommands = Integer.parseInt(reader.readLine());

        Vehicle car = new Car(Double.parseDouble(carInfo[1]),Double.parseDouble(carInfo[2]));
        Vehicle truck = new Truck(Double.parseDouble(truckInfo[1]),Double.parseDouble(truckInfo[2]));

        for (int i = 0; i < numberOfCommands; i++) {
            String[]tokens = reader.readLine().split("\\s+");

            String command = tokens[0];

            switch (command){
                case "Drive":
                    if (tokens[1].equals("Car")){
                        car.driving(Double.parseDouble(tokens[2]));
                    }else{
                        truck.driving(Double.parseDouble(tokens[2]));
                    }
                    break;
                case  "Refuel":
                    if (tokens[1].equals("Car")){
                        car.refueling(Double.parseDouble(tokens[2]));
                    }else{
                        truck.refueling(Double.parseDouble(tokens[2]));
                    }
            }
        }

        System.out.printf("Car: %.2f%n",((Car) car).getFuelQuantity());
        System.out.printf("Truck: %.2f",((Truck) truck).getFuelQuantity());
    }
}
