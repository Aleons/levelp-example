package ru.levelp.example.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "KIND", discriminatorType = DiscriminatorType.STRING)
@Table(name = "USERS")
public abstract class User {
    @Id
    @Column(length = 50)
    private String login;

    @Column(length = 200)
    private String displayName;

    @Column
    private String encryptedPassword;

    public User(String login, String displayName) {
        this.login = login;
        this.displayName = displayName;
    }

    public User() {
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
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
