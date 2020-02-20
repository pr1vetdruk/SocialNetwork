package ru.privetdruk.socialnetwork.domain.user.dto;

import org.springframework.util.StringUtils;
import ru.privetdruk.socialnetwork.domain.user.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class UserDto {
    @NotBlank(message = "{validation.global.notEmpty}")
    @Size(min = 2, max = 32, message = "{validation.registration.firstName.size}")
    private String login;
    @NotBlank(message = "{validation.global.notEmpty}")
    @Size(min = 2, max = 32, message = "{validation.registration.firstName.size}")
    private String password;
    @NotBlank(message = "{validation.global.notEmpty}")
    private String passwordConfirmation;
    @NotBlank(message = "{validation.global.notEmpty}")
    @Email(message = "{validation.email}")
    private String email;

    public UserDto(String login, String password, String passwordConfirmation, String email) {
        this.login = login;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.email = email;
    }

    public User convert() {
        User user = new User();
        user.setLogin(this.login);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setDateCreation(new Date());
        return user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = StringUtils.isEmpty(login) ? null : login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = StringUtils.isEmpty(password) ? null : password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = StringUtils.isEmpty(passwordConfirmation) ? null : passwordConfirmation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = StringUtils.isEmpty(email) ? null : email;
    }
}
