package ru.mycash.cash.model;

import java.util.Date;

public class User extends AbstractNamedEntity {

    private String email;

    private String password;

    private Date registered = new Date();

    public User(Integer id, String name, String email, String password) {
        super(id, name);
        this.email = email;
        this.password = password;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", email=" + email +
                ", name=" + name +
                ')';
    }
}