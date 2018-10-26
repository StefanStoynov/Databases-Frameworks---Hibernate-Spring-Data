package oopPrinciples.p05_BorderControl;

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

            if (input.length == 2){
                Robot robot = new Robot(input[0],input[1]);
                objects.add(robot);

            }else {
                Citizen citizen = new Citizen(input[0],Integer.parseInt(input[1]),input[2]);
                objects.add(citizen);
            }
            
        }
        String fakeId = reader.readLine();

        for (BaseObject object : objects) {
            if (object.getId().substring(object.getId().length()-fakeId.length()).equals(fakeId)){
                System.out.println(object.getId());
            }
        }

    }
}
