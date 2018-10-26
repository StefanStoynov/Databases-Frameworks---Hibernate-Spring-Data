package oopPrinciples.p02_MultipleImplementation;

import oopPrinciples.p02_MultipleImplementation.Interfaces.Birthable;
import oopPrinciples.p02_MultipleImplementation.Interfaces.Identifiable;
import oopPrinciples.p02_MultipleImplementation.Interfaces.Person;

public class Citizen implements Person, Birthable, Identifiable {
    private String name;
    private int age;
    private String id;
    private String birthdate;

    public Citizen(String name, int age, String id, String birthdate) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthdate = birthdate;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public String getBirthdate() {
        return this.birthdate;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
