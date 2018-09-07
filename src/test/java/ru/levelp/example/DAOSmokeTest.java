package ru.levelp.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelp.example.dao.EventsDAO;
import ru.levelp.example.dao.UsersDAO;
import ru.levelp.example.model.Customer;
import ru.levelp.example.model.Event;
import ru.levelp.example.model.User;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DAOSmokeTest {
    @Autowired
    private EntityManager em;

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private EventsDAO eventsDAO;

    @Test
    public void userAdd() {
        User user = new Customer("tester", "Test User");
        usersDAO.add(user);

        assertEquals(user, em.find(User.class, "tester"));
    }

    @Test(expected = EntityExistsException.class)
    public void userAddConstraintViolation() {
        usersDAO.add(new Customer("tester", "Test User"));
        usersDAO.add(new Customer("tester", "Test User"));
    }

    @Test
    public void eventsAddAndSearch() {
        Date now = new Date();
        Event event = new Event("test", "test", now, now);

        eventsDAO.add(event);

        List<Event> found = eventsDAO.findEvents(now);
        assertEquals(1, found.size());
        assertEquals(event, found.get(0));
    }
}
