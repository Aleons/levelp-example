package ru.levelp.example;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ru.levelp.example.dao.TicketsDAO;
import ru.levelp.example.logic.Orders;
import ru.levelp.example.model.Cart;
import ru.levelp.example.model.Event;
import ru.levelp.example.model.Ticket;
import ru.levelp.example.model.User;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrdersTest {
    @Test
    public void testAccept() {
        User user = new User("test-user", "Test user");
        Cart cart = new Cart(user);

        Event event = new Event("test event", "",
                new Date(), new Date());

        Ticket testTicket = new Ticket(user, event);

        cart.add(testTicket);

        AtomicBoolean addInvoked = new AtomicBoolean(false);
        TicketsDAO mockDAO = new TicketsDAO() {
            @Override
            public List<Ticket> findByEvent(Event event) {
                Assert.fail("findByEvent shouldn't be invoked");
                return null;
            }

            @Override
            public void add(Ticket ticket) {
                assertEquals(testTicket, ticket);
                addInvoked.set(true);
            }
        };

        Orders orders = new Orders(mockDAO);

        orders.accept(cart);

        assertTrue("Method add hasn't been invoked",
                addInvoked.get());

        assertEquals("Cart should be empty",
                0, cart.getTickets().size());
    }

    @Test
    public void testAcceptUsingMockito() {
        User user = new User("test-user", "Test user");
        Cart cart = new Cart(user);

        Event event = new Event("test event", "",
                new Date(), new Date());

        Ticket testTicket = new Ticket(user, event);

        cart.add(testTicket);

        TicketsDAO mockDAO = Mockito.mock(TicketsDAO.class);

        Orders orders = new Orders(mockDAO);

        orders.accept(cart);

        Mockito.verify(mockDAO, Mockito.times(1))
                .add(testTicket);

        Mockito.verify(mockDAO, Mockito.never())
                .findByEvent(Mockito.anyObject());

        assertEquals("Cart should be empty",
                0, cart.getTickets().size());
    }
}
