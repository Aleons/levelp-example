package ru.levelp.example.model;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(length = 50)
    private String login;

    @Column(length = 200)
    private String displayName;

    public User(String login, String displayName) {
        this.login = login;
        this.displayName = displayName;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
