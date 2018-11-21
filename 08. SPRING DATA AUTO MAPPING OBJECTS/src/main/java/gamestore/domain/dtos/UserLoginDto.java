package gamestore.domain.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserLoginDto {
    private String email;
    private String password;

    //<editor-fold desc="Constructors">
    public UserLoginDto() {
    }

    public UserLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
    //</editor-fold>

    //<editor-fold desc="Getters">
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-z]{2,4}", message = "Incorrect email.")
    public String getEmail() {
        return this.email;
    }

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?!.*[&%$]).{6,}$", message = "Password length must be at least 6 symbols and must contain at least 1 uppercase, 1 lowercase letter and 1 digit.")
    public String getPassword() {
        return this.password;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //</editor-fold>
}
