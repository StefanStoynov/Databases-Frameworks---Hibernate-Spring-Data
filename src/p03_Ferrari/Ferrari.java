package p03_Ferrari;

public class Ferrari implements Car{
    private String model;
    private String driver;

    public Ferrari(String driver) {
        this.model = "488-Spider";
        this.driver = driver;
    }

    public String getModel() {
        return this.model;
    }

    @Override
    public String getDriversName() {
        return this.driver;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        //488-Spider/Brakes!/Zadu6avam sA!/Bat Giorgi
        sb.append(this.model).append("/Brakes!/").append("Zadu6avam sA!/").append(this.driver);
        return sb.toString();
    }
}
