package ru.levelp.example.model;

public class Ticket {
    private User user;
    private Event event;

    public Ticket(User user, Event event) {
        if (user == null) throw new IllegalArgumentException("user shouldn't be null");
        if (event == null) throw new IllegalArgumentException("event shouldn't be null");

        this.user = user;
        this.event = event;
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
