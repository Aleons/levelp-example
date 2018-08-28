package ru.levelp.example.model;

import javax.persistence.*;

@Entity
@Table(name = "TICKETS")
public class Ticket {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;

    public Ticket() {
    }

    public Ticket(User user, Event event) {
        if (user == null) throw new IllegalArgumentException("user shouldn't be null");
        if (event == null) throw new IllegalArgumentException("event shouldn't be null");

        this.user = user;
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
