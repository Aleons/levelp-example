package ru.levelp.example.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levelp.example.dao.TicketsDAO;
import ru.levelp.example.model.Cart;
import ru.levelp.example.model.Ticket;

@Service
public class Orders {
    private final TicketsDAO tickets;

    @Autowired
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
