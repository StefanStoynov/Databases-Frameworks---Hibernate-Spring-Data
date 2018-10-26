package oopPrinciples.p04_Telephony;

import java.util.ArrayList;
import java.util.List;

public class Smarthphone implements Callable, Browsable {

    private List<String> numbers;
    private List<String> pages;

    public Smarthphone() {
        this.numbers = new ArrayList<>();
        this.pages = new ArrayList<>();
    }

    public void addNumber(String number){
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))){
                this.numbers.add("Invalid number!");
                return;
            }

        }
        this.numbers.add(number);
    }

    public void addPage(String page){
        for (int i = 0; i < page.length(); i++) {
            if (Character.isDigit(page.charAt(i))){
                this.pages.add("Invalid URL!");
                return;
            }

        }
        this.pages.add(page);
    }

    @Override
    public List<String> browse() {
        return this.pages;
    }

    @Override
    public List<String> call() {
        return this.numbers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String number : numbers) {
            if ("Invalid number!".equals(number)){
                sb.append(number).append(System.lineSeparator());
            }else {
                sb.append("Calling... ").append(number).append(System.lineSeparator());
            }

        }
        for (String page : pages) {
            if ("Invalid URL!".equals(page)){
                sb.append(page).append(System.lineSeparator());
            }else{
                sb.append("Browsing: ").append(page).append("!").append(System.lineSeparator());
            }

        }
        return sb.toString();
    }
}
