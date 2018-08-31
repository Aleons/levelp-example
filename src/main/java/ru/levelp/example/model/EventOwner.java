package ru.levelp.example.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "owner")
public class EventOwner extends User {
    public EventOwner(String login, String displayName) {
        super(login, displayName);
    }

    public EventOwner() {
    }

    @Column
    private String tel;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
