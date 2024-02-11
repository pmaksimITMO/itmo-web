package ru.itmo.wp.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserCredentials {
    @NotEmpty
    @Size(min = 2, max = 24)
    @Pattern(regexp = "[a-z]{2,24}", message = "Expected lowercase Latin letters")
    private String login;

    @NotEmpty
    @Size(min = 1, max = 60)
    private String password;

    @Size(max = 100)
    @Pattern(regexp = "[a-z]{0,100}", message = "Expected lowercase Latin letters")
    private String name;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
