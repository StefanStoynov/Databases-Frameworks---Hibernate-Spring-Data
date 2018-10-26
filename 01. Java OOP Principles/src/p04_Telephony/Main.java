package oopPrinciples.p04_Telephony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();
        Smarthphone smarthphone = new Smarthphone();

            if (line.length()>0){
                String[]input = line.split("\\s+");
                for (String number : input) {
                    smarthphone.addNumber(number);
                }
            }

            String line2 = reader.readLine();

            if (line2.length()>0){
                String[]input = line2.split("\\s+");
                for (String page : input) {
                    smarthphone.addPage(page);
                }
            }

            System.out.println(smarthphone.toString());

    }
}
