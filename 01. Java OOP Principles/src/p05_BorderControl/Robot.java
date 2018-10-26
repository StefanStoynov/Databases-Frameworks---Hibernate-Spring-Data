package oopPrinciples.p05_BorderControl;

public class Robot implements BaseObject {
    private String model;
    private String id;

    public Robot(String model, String id) {
        this.model = model;
        this.id = id;
    }


    @Override
    public String getId() {
        return this.id;
    }
}
