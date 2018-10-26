package oopPrinciples.p03_Ferrari;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String driversName = reader.readLine();

        Car ferrari = new Ferrari(driversName);
        System.out.println(ferrari.toString());


    }
}
