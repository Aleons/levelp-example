package ru.levelp.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.levelp.example.dao.EventsDAO;
import ru.levelp.example.dao.EventsDAOImpl;
import ru.levelp.example.dao.UsersDAO;
import ru.levelp.example.dao.UsersDAOImpl;
import ru.levelp.example.model.Event;
import ru.levelp.example.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DAOSmokeTest {
    private EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void setup() {
        emf = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        em = emf.createEntityManager();
    }

    @After
    public void after() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    public void userAdd() {
        UsersDAO usersDAO = new UsersDAOImpl(em);

        User user = new User("tester", "Test User");
        usersDAO.add(user);

        assertEquals(user, em.find(User.class, "tester"));
    }

    @Test
    public void eventsAddAndSearch() {
        Date now = new Date();
        Event event = new Event("test", "test", now, now);
        EventsDAO dao = new EventsDAOImpl(em);

        dao.add(event);

        List<Event> found = dao.findEvents(now);
        assertEquals(1, found.size());
        assertEquals(event, found.get(0));
    }
}
