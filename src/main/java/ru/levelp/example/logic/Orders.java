package ru.levelp.example.logic;

import ru.levelp.example.dao.TicketsDAO;
import ru.levelp.example.model.Cart;
import ru.levelp.example.model.Ticket;

public class Orders {
    private final TicketsDAO tickets;

    public Orders(TicketsDAO tickets) {
        this.tickets = tickets;
    }

    public void accept(Cart cart) {
        for (Ticket ticket : cart.getTickets()) {
            tickets.add(ticket);
        }

        cart.clear();
    }
}
