package p07_Mankind;

public class Worker extends Human{
    private double weekSalary;
    private double workHowersDay;

    public Worker(String firstName, String lastName, double weekSalary, double workHowersDay) {
        super(firstName, lastName);
        this.setWeekSalary(weekSalary);
        this.setWorkHowersDay(workHowersDay);
    }

    private double SalaryPerHour(){
        return this.weekSalary / (this.workHowersDay * 7);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("%.2f",this.weekSalary)).append(System.lineSeparator());
        sb.append(String.format("%.2f",this.workHowersDay)).append(System.lineSeparator());
        sb.append(String.format("%.2f",this.SalaryPerHour())).append(System.lineSeparator());
        return sb.toString();
    }

    public void setWeekSalary(double weekSalary) {
        if (weekSalary< 10){
            throw new IllegalArgumentException("Expected value mismatch!Argument: weekSalary");
        }
        this.weekSalary = weekSalary;
    }

    public void setWorkHowersDay(double workHowersDay) {
        if (workHowersDay<1 || workHowersDay>12){
            throw new IllegalArgumentException("Expected value mismatch!Argument: workHoursPerDay");
        }
        this.workHowersDay = workHowersDay;
    }
}

