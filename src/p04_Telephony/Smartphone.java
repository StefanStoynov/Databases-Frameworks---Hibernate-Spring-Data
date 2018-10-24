package p04_Telephony;

import p04_Telephony.Interfaces.Browsable;
import p04_Telephony.Interfaces.Callable;

import java.util.ArrayList;
import java.util.List;

public class Smartphone implements Browsable, Callable {
    private List<String> number;
    private List<String> webpage;

    public Smartphone() {
        this.number = new ArrayList<>();
        this.webpage = new ArrayList<>();
    }

    @Override
    public String browse() {
        return null;
    }

    @Override
    public String call() {
        return null;
    }

    public void addNumber(String number) {
        for (int i = 0; i <number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                throw new IllegalArgumentException("Invalid number!");
            }
        }
        this.number.add(number);
    }

    public void addWebpage(String webpage){
        for (int i = 0; i < webpage.length(); i++) {
            if (Character.isDigit(webpage.charAt(i))){
                throw new IllegalArgumentException("Invalid URL!");
            }
        }
        this.webpage.add(webpage);
    }

    public List<String> getNumber() {
        return this.number;
    }

    public List<String> getWebpage() {
        return this.webpage;
    }
}
