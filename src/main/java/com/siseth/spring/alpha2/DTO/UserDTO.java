package com.siseth.spring.alpha2.DTO;

import com.siseth.spring.alpha2.validators.PasswordMatches;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@PasswordMatches
public class UserDTO {

    private Long adminId;
    @Email(message = "Podaj prawidlowy email")
    private String email;

    @NotBlank(message = "Haslo nie moze byc puste")
    private String password;
    private String repeatPassword;

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

}
