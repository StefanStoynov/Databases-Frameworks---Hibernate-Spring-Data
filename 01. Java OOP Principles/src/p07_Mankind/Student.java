package p07_Mankind;

public class Student extends Human {
    private String facultyNumber;

    public Student(String firstName, String lastName, String facultyNumber) {
        super(firstName, lastName);
        this.setFacultyNumber(facultyNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Faculty number: ").append(this.facultyNumber).append(System.lineSeparator());
        return sb.toString();
    }

    public void setFacultyNumber(String facultyNumber) {
        if (4 > facultyNumber.length() || facultyNumber.length() > 11){
            throw new IllegalArgumentException("Invalid faculty number!");
        }
        this.facultyNumber = facultyNumber;
    }
}
