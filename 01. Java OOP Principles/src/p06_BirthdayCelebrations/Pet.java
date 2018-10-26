package oopPrinciples.p06_BirthdayCelebrations;

public class Pet implements BaseObject {
    private String name;
    private String birthday;

    public Pet(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String getBirthdate() {
        return this.birthday;
    }

}
