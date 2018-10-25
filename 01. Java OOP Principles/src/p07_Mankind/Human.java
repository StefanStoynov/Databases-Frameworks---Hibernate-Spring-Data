package p07_Mankind;

public abstract class Human {
    private String firstName;
    private String lastName;

    public Human(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("First Name: ").append(this.firstName).append(System.lineSeparator());
        sb.append("Last Name: ").append(this.lastName).append(System.lineSeparator());
        return sb.toString();
    }

    public void setFirstName(String firstName) {
        if (!firstName.substring(0,1).equals(firstName.substring(0,1).toUpperCase())){
            throw new IllegalArgumentException("Expected upper case letter!Argument: firstName");
        }
        if (firstName.length()<4){
            throw new IllegalArgumentException("Expected length at least 4 symbols!Argument: firstName");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (!lastName.substring(0,1).equals(lastName.substring(0,1).toUpperCase())){
            throw new IllegalArgumentException("Expected upper case letter!Argument: lastName");
        }
        if (lastName.length()<3){
            throw new IllegalArgumentException("Expected length at least 3 symbols!Argument: lastName");
        }
        this.lastName = lastName;
    }
}

