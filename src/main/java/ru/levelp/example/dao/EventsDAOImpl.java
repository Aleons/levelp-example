package ru.levelp.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levelp.example.model.Event;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.List;

@Service
public class EventsDAOImpl implements EventsDAO {
    private EntityManager em;

    @Autowired
    public EventsDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Event> findEvents(Date date) {
        return em.createQuery("SELECT e FROM Event e where e.start >= :date", Event.class)
                    .setParameter("date", date)
                .getResultList();
    }

    @Override
    public void add(Event event) {
        em.getTransaction().begin();
        try {
            em.persist(event);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
