package ru.levelp.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levelp.example.model.Event;
import ru.levelp.example.model.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

@Service
public class TicketsDAOImpl implements TicketsDAO {
    @Autowired
    private EntityManager em;

    @Override
    public List<Ticket> findByEvent(Event event) {
        return event.getTickets();
    }

    @Override
    public void add(Ticket ticket) {
        em.getTransaction().begin();
        try {
            em.persist(ticket);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
