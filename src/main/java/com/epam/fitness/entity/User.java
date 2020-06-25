package com.epam.fitness.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private String surName;
    private LocalDate birthDate;
    private Role role;

    public User(int id, String login, String password, String name, String surName, LocalDate birthDate, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surName = surName;
        this.birthDate = birthDate;
        this.role = role;
    }

    public User(String login, String password, String name, String surName, LocalDate birthDate, Role role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surName = surName;
        this.birthDate = birthDate;
        this.role = role;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
