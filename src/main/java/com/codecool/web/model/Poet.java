package com.codecool.web.model;

public class Poet extends AbstractModel {

    private final String name;
    private final String email;
    private final String password;

    public Poet(int id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
