package ru.levelp.example.dao;

import ru.levelp.example.model.User;

public interface UsersDAO {
    User findByLogin(String login);
    void add(User user);
}
