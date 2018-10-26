package oopPrinciples.p06_BirthdayCelebrations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<BaseObject> objects = new LinkedList<>();
        String line;
        while (true) {
            if ("End".equals(line = reader.readLine())) {
                break;
            }
            String[]input  = line.split("\\s+");

            if (input[0].equals("Robot")){
               continue;
            }else if(input[0].equals("Citizen")){
                BaseObject citizen = new Citizen(input[1],Integer.parseInt(input[2]),input[3],input[4]);
                objects.add(citizen);
            }
            else {
                BaseObject pet = new Pet(input[1],input[2]);
                objects.add(pet);
            }
        }
        String birthdate = reader.readLine();

        for (BaseObject object : objects) {
            if (object.getBirthdate().substring(object.getBirthdate().length()-birthdate.length()).equals(birthdate)){
                System.out.println(object.getBirthdate());
            }
        }

    }
}
