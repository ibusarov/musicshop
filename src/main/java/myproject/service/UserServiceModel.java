package myproject.service;

import myproject.model.entity.Role;

import java.util.Set;

public class UserServiceModel extends BaseServiceModel {

    private String email;
    private String password;
    private String repeatPassword;


    public UserServiceModel() {
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
