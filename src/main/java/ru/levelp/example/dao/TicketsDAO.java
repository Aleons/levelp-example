package ru.levelp.example.dao;

import ru.levelp.example.model.Event;
import ru.levelp.example.model.Ticket;

import java.util.List;

public interface TicketsDAO {
    List<Ticket> findByEvent(Event event);
    void add(Ticket ticket);
}
