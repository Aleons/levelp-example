package ru.levelp.example.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final User user;
    private ArrayList<Ticket> tickets =
            new ArrayList<>();

    public Cart(User user) {
        if (user == null) throw new IllegalArgumentException("user shouldn't be null");
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void add(Ticket ticket) {
        if (ticket.getUser() != user) {
            throw new IllegalArgumentException("Ticket's user should be " + user.getLogin());
        }
        tickets.add(ticket);
    }

    public void clear() {
        tickets.clear();
    }
}
