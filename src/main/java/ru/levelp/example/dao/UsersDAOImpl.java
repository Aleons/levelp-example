package ru.levelp.example.dao;

import ru.levelp.example.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

public class UsersDAOImpl implements UsersDAO {
    private EntityManager em;

    public UsersDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public User findByLogin(String login) {
        return em.find(User.class, login);
    }

    @Override
    public void add(User user) {
        em.getTransaction().begin();
        try {
            em.persist(user);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
