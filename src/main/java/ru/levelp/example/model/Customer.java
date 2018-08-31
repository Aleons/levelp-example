package ru.levelp.example.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "customer")
public class Customer extends User {
    public Customer(String login, String displayName) {
        super(login, displayName);
    }

    public Customer() {
    }
}
