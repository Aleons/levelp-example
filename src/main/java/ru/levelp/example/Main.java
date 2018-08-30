package ru.levelp.example;

import ru.levelp.example.dao.UsersDAO;
import ru.levelp.example.dao.UsersDAOImpl;
import ru.levelp.example.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public int sum(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        try {
            UsersDAO usersDAO = new UsersDAOImpl(em);

            User user = new User("tester", "Test USer");
            usersDAO.add(user);
        } finally {
            em.close();
            emf.close();
        }
    }
}
