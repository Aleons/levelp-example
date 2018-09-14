package ru.levelp.example.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.levelp.example.dao.TicketsDAO;
import ru.levelp.example.model.Cart;
import ru.levelp.example.model.Ticket;

import java.util.List;

@Service
public class Orders {
    private final TicketsDAO tickets;

    @Autowired(required = false)
    @Qualifier("VK")
    private SocialNetwork vk;

    @Autowired
    private List<SocialNetwork> allNetworks;

    @Autowired
    public Orders(TicketsDAO tickets) {
        this.tickets = tickets;
    }

    public void accept(Cart cart) {
        for (Ticket ticket : cart.getTickets()) {
            tickets.add(ticket);
        }

        cart.clear();

        for (SocialNetwork socialNetwork : allNetworks) {
            socialNetwork.postMessage("...", "Tickets sold");
        }
    }
}
