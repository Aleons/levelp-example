package ru.levelp.example.model;

public class User {
    private String login;
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
