package p04_Telephony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Smartphone smartphone = new Smartphone();

        String line = reader.readLine();
        String[]numbers = line.split("\\s+");
        for (int i = 0; i < numbers.length; i++) {
            smartphone.addNumber(numbers[i]);
            System.out.printf("Calling... %s%n",numbers[i]);
        }

        line = reader.readLine();
        String[]pages = line.split("\\s+");
        for (int i = 0; i < pages.length; i++) {
            smartphone.addWebpage(pages[i]);
            System.out.printf("Browsing: %s%n",pages[i]);
        }









    }
}
